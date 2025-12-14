package com.ClotheShop.CShop.Service.User;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Security.JWTService;
import com.ClotheShop.CShop.Security.SDTO.JwtTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInterfaceImpl implements UserInterface{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JWTService jwtService;

    @Autowired
    public UserInterfaceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, JWTService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public boolean comparePasswords(String token , String fromServicePassword) {

            boolean result = false;
            JwtTokenDTO jwtTokenDTO = new JwtTokenDTO();
            jwtTokenDTO.setToken(token);
            String Login = jwtService.getLoginFromToken(token);
            User certainUser = userRepository.findByLogin(Login).get();
        //userRepository.findByLogin(jwtService.parseTokenForLogin(jwtTokenDTO).getLogin()).get();

            if(passwordEncoder.matches(fromServicePassword,certainUser.getPassword())){
                result = true;
            }

            return result;

    }

}