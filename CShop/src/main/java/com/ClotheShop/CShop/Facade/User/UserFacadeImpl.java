package com.ClotheShop.CShop.Facade.User;

import com.ClotheShop.CShop.DTO.UserDTO.UserDTO;
import com.ClotheShop.CShop.DTO.UserDTO.UserDepositDTO;
import com.ClotheShop.CShop.Mapper.UserMapper;
import com.ClotheShop.CShop.Security.SDTO.JwtAuthenticationDTO;
import com.ClotheShop.CShop.Security.SDTO.JwtTokenDTO;
import com.ClotheShop.CShop.DTO.UserDTO.UserCredentialDTO;
import com.ClotheShop.CShop.DTO.UserDTO.VerifyChangeDTO;
import com.ClotheShop.CShop.Service.User.UserInterface;
import com.ClotheShop.CShop.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserInterface userInterface;

    @Autowired
    public UserFacadeImpl(UserService userService, UserMapper userMapper, UserInterface userInterface) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userInterface = userInterface;
    }

    @Override
    public UserDTO addUser(UserDTO user) {
        return userMapper.toDTO(userService.addUser(userMapper.toUser(user)));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(x-> userMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(int id) {
        return userMapper.toDTO(userService.getUserById(id));
    }

    @Override
    public UserDTO updateUserById(int id, UserDTO user) {
        return userMapper.toDTO(userService.updateUserById(id,userMapper.toUser(user)));
    }

    @Override
    public void deleteUserById(int id) {
        userService.deleteUserById(id);
    }

    @Override
    public JwtAuthenticationDTO signIn(UserCredentialDTO userCredentialDTO) {
        return userService.signIn(userCredentialDTO);
    }

    @Override
    public UserDTO changeUserYourSelf(String token, VerifyChangeDTO dto) {

        String bearerToken = getPayLoadToken(token);

        if(userInterface.comparePasswords(bearerToken,dto.getOldPassword())){

            return userMapper.toDTO(userService.changeUserYourSelf(bearerToken,dto));

        }
        return null;
    }

    @Override
    public void deleteUserYourSelf(String token) {

        String bearerToken = getPayLoadToken(token);

        userService.deleteUserYourSelf(bearerToken);
    }

    @Override
    public UserDTO getYourSelf(String token) {

        String bearerToken = getPayLoadToken(token);

        return userMapper.toDTO(userService.getYourSelf(bearerToken));
    }

    @Override
    public JwtTokenDTO getOut(String token) {

        String bearerToken = getPayLoadToken(token);

        return userService.getOut(bearerToken);
    }

    @Override
    public UserDTO deposit(String token, UserDepositDTO depositSum) {

        String bearerToken = getPayLoadToken(token);

        return userMapper.toDTO(userService.deposit(bearerToken,depositSum.getDeposit()));
    }

    //Получить часть токена с полезной информации
    private String getPayLoadToken(String token){

        String payLoadData = token;

        if(payLoadData != null && payLoadData.startsWith("Bearer ")) {
            payLoadData = payLoadData.substring(7).trim();
        }

        return payLoadData;
    }

}