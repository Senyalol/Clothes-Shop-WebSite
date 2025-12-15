package com.ClotheShop.CShop.Mapper;

import com.ClotheShop.CShop.DTO.UserDTO.UserDTO;
import com.ClotheShop.CShop.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //Из сущности в DTO
    public UserDTO toDTO(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setUser_id(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBalance(user.getBalance());
        userDTO.setSecretKey(user.getSecretKey());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    //Из DTO в сущность
    public User toUser(UserDTO userDTO) {

        User user = new User();

        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBalance(userDTO.getBalance());
        user.setSecretKey(userDTO.getSecretKey());
        user.setRole(userDTO.getRole());

        return user;

    }

}
