package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.UserDTO;
import com.ClotheShop.CShop.Facade.User.UserFacade;
import com.ClotheShop.CShop.Security.SDTO.JwtAuthenticationDTO;
import com.ClotheShop.CShop.Security.SDTO.UserCredentialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return userFacade.getUserById(id);
    }

    @PostMapping
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        return userFacade.addUser(userDTO);
    }


    @PostMapping("/auth")
    public JwtAuthenticationDTO signIn(@RequestBody UserCredentialDTO userCredentialDTO){
        return userFacade.signIn(userCredentialDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userFacade.deleteUserById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        return userFacade.updateUserById(id, userDTO);
    }

}