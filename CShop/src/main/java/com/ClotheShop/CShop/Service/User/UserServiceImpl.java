package com.ClotheShop.CShop.Service.User;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Security.JWTService;
import com.ClotheShop.CShop.Security.SDTO.JwtAuthenticationDTO;
import com.ClotheShop.CShop.Security.SDTO.JwtTokenDTO;
import com.ClotheShop.CShop.Security.SDTO.UserCredentialDTO;
import com.ClotheShop.CShop.Security.SDTO.VerifyChangeDTO;
import com.ClotheShop.CShop.Service.User.Checks.CreateChecks.*;
import com.ClotheShop.CShop.Service.User.Checks.UpdateChecks.*;
import com.ClotheShop.CShop.Service.User.Checks.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Value("${app.secretKey}")
    private String secretKey;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,JWTService jwtService,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User addUser(User user) {

        List<UserCreateCheck> cChecks = Arrays.asList(
            new UserLoginCreateCheck(userRepository),
            new UserPasswordCreateCheck(),
            new UserFirstNameCreateCheck(),
            new UserLastNameCreateCheck(),
            new UserBalanceCreateCheck()
        );

        MainUserCreateCheck createChecks = new MainUserCreateCheck(cChecks);

        if(createChecks.check(user)){

            user.setBalance(0.0);

            if(user.getSecretKey() != null && user.getSecretKey().equals(secretKey)){
               user.setRole(UserRoles.ADMIN.name());
            }

            else{
                user.setRole(UserRoles.USER.name());
            }

            user.setSecretKey(passwordEncoder.encode(user.getSecretKey()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            LOGGER.info("User : {} successfully added", user.getLogin());
            return user;
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public User updateUserById(int id, User user) {

        User certainUser = userRepository.findById(id).get();
        String oldLogin = certainUser.getLogin();
        List<UserUpdateCheck> updateChecks = Arrays.asList(
                new UserLoginUpdateCheck(userRepository),
                new UserPasswordUpdateCheck(passwordEncoder),
                new UserFirstNameUpdateCheck(),
                new UserLastNameUpdateCheck(),
                new UserBalanceUpdateCheck(),
                new UserSecretKeyUpdateCheck()
        );

        MainUserUpdateCheck userUpdateCheck = new MainUserUpdateCheck(updateChecks);

        try{

            userUpdateCheck.applyChecks(certainUser, user);
            LOGGER.info("User : {} successfully updated", oldLogin);
        }
        catch(Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException();
        }


        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void deleteUserById(int id) {
        String certainLogin = userRepository.findById(id).get().getLogin();
        userRepository.deleteById(id);
        LOGGER.info("User: {} successfully deleted",certainLogin);
    }

    @Override
    public JwtAuthenticationDTO signIn(UserCredentialDTO userCredentialDTO){

        User certainUser = userRepository.findByLogin(userCredentialDTO.getLogin()).get();
        JwtAuthenticationDTO authenticationDTO = new JwtAuthenticationDTO();
        authenticationDTO.setToken("Error authentication");
        authenticationDTO.setRefreshToken("Error refresh token");

        if(userCredentialDTO.getLogin() != null && userCredentialDTO.getPassword() != null && userRepository.findByLogin(userCredentialDTO.getLogin()).isPresent()){

            if(passwordEncoder.matches(userCredentialDTO.getPassword(),certainUser.getPassword())){

                LOGGER.info("{} User {} successfully authenticated",certainUser.getId(),certainUser.getLogin());
                return jwtService.getTokenForUser(userCredentialDTO.getLogin());
            }

            else{
                LOGGER.error("Incorrect login or password");
            }

        }

        else{
            LOGGER.error("User with login - {} not found",userCredentialDTO.getLogin());
        }

        return authenticationDTO;
    }

    //Достать пользователя по его JWT
    public User userFromToken(String token){

        JwtTokenDTO yourToken = new JwtTokenDTO();
        yourToken.setToken(token);
        String yoursLogin = jwtService.parseTokenForLogin(yourToken).getLogin();
        User you = userRepository.findByLogin(yoursLogin).get();

        return you;
    }

    @Transactional
    @Override
    public User changeUserYourSelf(String token, VerifyChangeDTO user) {

        User changedUser = userFromToken(token);

        List<UserUpdateCheck> updateChecks = Arrays.asList(
                new UserLoginUpdateCheck(userRepository),
                new UserPasswordUpdateCheck(passwordEncoder),
                new UserFirstNameUpdateCheck(),
                new UserLastNameUpdateCheck(),
                new UserBalanceUpdateCheck(),
                new UserSecretKeyUpdateCheck()
        );

        MainUserUpdateCheck userUpdateCheck = new MainUserUpdateCheck(updateChecks);

        try{

            userUpdateCheck.applyUserChecks(changedUser, user);
            LOGGER.info("User : {} successfully updated", changedUser.getLogin());
            return changedUser;

        }
        catch(Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException();
        }

    }

    //Удалить свой аккаунт
    @Transactional
    @Override
    public void deleteUserYourSelf(String token) {

        int yourId = userFromToken(token).getId();
        userRepository.deleteById(yourId);

    }

    //Получить пользователю самого себе
    @Override
    public User getYourSelf(String token) {
        return userFromToken(token);
    }

}