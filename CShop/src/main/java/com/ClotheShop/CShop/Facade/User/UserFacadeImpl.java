package com.ClotheShop.CShop.Facade.User;

import com.ClotheShop.CShop.DTO.UserDTO;
import com.ClotheShop.CShop.Mapper.UserMapper;
import com.ClotheShop.CShop.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserFacadeImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
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
}
