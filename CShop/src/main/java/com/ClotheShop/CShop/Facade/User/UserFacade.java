package com.ClotheShop.CShop.Facade.User;

import com.ClotheShop.CShop.DTO.UserDTO;
import com.ClotheShop.CShop.Security.SDTO.JwtAuthenticationDTO;
import com.ClotheShop.CShop.Security.SDTO.UserCredentialDTO;

import java.util.List;

public interface UserFacade {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(int id);

    UserDTO addUser(UserDTO user);

    UserDTO updateUserById(int id,UserDTO user);

    void deleteUserById(int id);

    JwtAuthenticationDTO signIn(UserCredentialDTO userCredentialDTO);

}
