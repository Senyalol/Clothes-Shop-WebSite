package com.ClotheShop.CShop.Service.User;

import java.util.List;
import com.ClotheShop.CShop.Entity.User;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    User addUser(User user);

    User updateUserById(int id,User user);

    void deleteUserById(int id);

}