package com.ClotheShop.CShop.Controller;

import com.ClotheShop.CShop.DTO.UserDTO.UserDTO;
import com.ClotheShop.CShop.DTO.UserDTO.UserDepositDTO;
import com.ClotheShop.CShop.Facade.User.UserFacade;
import com.ClotheShop.CShop.Security.SDTO.JwtAuthenticationDTO;
import com.ClotheShop.CShop.Security.SDTO.JwtTokenDTO;
import com.ClotheShop.CShop.DTO.UserDTO.UserCredentialDTO;
import com.ClotheShop.CShop.DTO.UserDTO.VerifyChangeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Сделать функционал для выхода из аккаунта

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5174"})
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    //Возможности админа

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

    //Пользовательский функционал

    @PostMapping("/reg")
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        return userFacade.addUser(userDTO);
    }

    @PostMapping("/auth")
    public JwtAuthenticationDTO signIn(@RequestBody UserCredentialDTO userCredentialDTO){
        return userFacade.signIn(userCredentialDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping("/getYours")
    public UserDTO getYourself(@RequestHeader("Authorization") String token) {
        return userFacade.getYourSelf(token);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PatchMapping("/updateYours")
    public UserDTO updateYourself(@RequestHeader("Authorization") String token ,@RequestBody VerifyChangeDTO dto) {
        return userFacade.changeUserYourSelf(token,dto);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @DeleteMapping("/deleteYours")
    public void deleteYourself(@RequestHeader("Authorization") String token) {
        userFacade.deleteUserYourSelf(token);
    }

    //Место для метода пополнения баланса
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @PatchMapping("/deposit")
    public UserDTO depositUser(@RequestHeader("Authorization") String token, @RequestBody UserDepositDTO userDepositDTO) {
        return userFacade.deposit(token,userDepositDTO);
    }

    //Модернизировать , токен нужно убивать
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping("/exit")
    public JwtTokenDTO exit(@RequestHeader("Authorization") String token){
        return userFacade.getOut(token);
    }

}