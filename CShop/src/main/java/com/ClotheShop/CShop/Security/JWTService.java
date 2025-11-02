package com.ClotheShop.CShop.Security;

import com.ClotheShop.CShop.Security.SDTO.JwtAuthenticationDTO;
import com.ClotheShop.CShop.Security.SDTO.JwtTokenDTO;
import com.ClotheShop.CShop.Security.SDTO.LoginFromTokenDTO;

public interface JWTService {

    boolean validateJwtToken(String token);

    String generateJwtToken(String login, String role);

    JwtAuthenticationDTO getTokenForUser(String login);

    JwtAuthenticationDTO generateRefreshToken(String login, String refreshToken);

    String getLoginFromToken(String token);

    LoginFromTokenDTO parseTokenForLogin(JwtTokenDTO token);

}