package com.ClotheShop.CShop.Facade.User;

import com.ClotheShop.CShop.DTO.UserDTO;

import java.util.List;

public interface UserFacade {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(int id);

    UserDTO addUser(UserDTO user);

    UserDTO updateUserById(int id,UserDTO user);

    void deleteUserById(int id);

}
