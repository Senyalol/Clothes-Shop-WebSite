package com.ClotheShop.CShop.Service.User;

import java.util.List;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Security.SDTO.JwtAuthenticationDTO;
import com.ClotheShop.CShop.Security.SDTO.JwtTokenDTO;
import com.ClotheShop.CShop.DTO.UserDTO.UserCredentialDTO;
import com.ClotheShop.CShop.DTO.UserDTO.VerifyChangeDTO;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    User addUser(User user);

    User updateUserById(int id,User user);

    void deleteUserById(int id);

    JwtAuthenticationDTO signIn(UserCredentialDTO userCredentialDTO);

    User changeUserYourSelf(String token, VerifyChangeDTO verifyChangeDTO);

    void deleteUserYourSelf(String token);

    User getYourSelf(String token);

    JwtTokenDTO getOut(String token);

    User deposit(String token, Double depositSum);

}