package com.ClotheShop.CShop.Service.User;

import java.util.List;
import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Security.SDTO.JwtAuthenticationDTO;
import com.ClotheShop.CShop.Security.SDTO.UserCredentialDTO;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    User addUser(User user);

    User updateUserById(int id,User user);

    void deleteUserById(int id);

    JwtAuthenticationDTO signIn(UserCredentialDTO userCredentialDTO);

}