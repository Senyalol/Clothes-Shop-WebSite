package com.ClotheShop.CShop.Security;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Security.SDTO.JwtAuthenticationDTO;
import com.ClotheShop.CShop.Security.SDTO.JwtTokenDTO;
import com.ClotheShop.CShop.Security.SDTO.LoginFromTokenDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class JWTServiceImpl implements JWTService {

    private final UserRepository userRepository;
    private static final Logger LOGGER = LogManager.getLogger(JWTServiceImpl.class);

    @Value("${app.signature_key}")
    private String signatureKey;

    @Autowired
    public JWTServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Получить подпись ключа
    private SecretKey getSignInKey(){
        try {
            byte[] keyBytes = Decoders.BASE64.decode(signatureKey);
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            throw new RuntimeException("Invalid secret key", e);
        }
    }

    //Получить роль пользователя
    private String getRoleUser(String login){

        User certainUser = userRepository.findByLogin(login).get();

        return certainUser.getRole();
    }

    //Валидация токена , проверка на ориг
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
            LOGGER.info("Token validated");
            return true;
        }

        catch (ExpiredJwtException e){
            LOGGER.warn("Token expired - {}",e.getMessage());
            System.out.println(e.getMessage());
            return false;
        }
        catch (UnsupportedJwtException e){
            LOGGER.warn("Token unsupported - {}",e.getMessage());
            System.out.println(e.getMessage());
            return false;
        }
        catch (MalformedJwtException e){
            LOGGER.warn("Token malformed - {}",e.getMessage());
            System.out.println(e.getMessage());
            return false;
        }
        catch (SecurityException e){
            LOGGER.warn("Security exception - {}",e.getMessage());
            System.out.println(e.getMessage());
            return false;
        }
        catch(Exception e){
            LOGGER.warn("Exception - {}",e.getMessage());
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Метод для генерации JWT токена
    public String generateJwtToken(String login, String role) {

        Date lifeTimeToken = Date.from(LocalDateTime.now().plusHours(11).atZone(ZoneId.systemDefault()).toInstant());

        return  Jwts.builder()
                .setSubject(login)
                .claim("role",role)
                .setExpiration(lifeTimeToken)
                .signWith(getSignInKey())
                .compact();

    }

    //Метод при прохождении аутентификации которого - пользователь получает токен
    public JwtAuthenticationDTO getTokenForUser(String login){

        String role = getRoleUser(login);
        JwtAuthenticationDTO jwt = new JwtAuthenticationDTO();
        jwt.setToken(generateJwtToken(login, role));
        jwt.setRefreshToken(generateJwtToken(login, role));

        return jwt;

    }

    //Генерация рефреш токена
    public JwtAuthenticationDTO generateRefreshToken(String login, String refreshToken){

        JwtAuthenticationDTO jwt = new JwtAuthenticationDTO();
        String role = getRoleUser(login);
        jwt.setToken(generateJwtToken(login, role));
        jwt.setRefreshToken(refreshToken);

        return jwt;
    }

    //Получить login по токену
    public String getLoginFromToken(String token){
        Claims login = Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
        return login.getSubject();
    }

    public LoginFromTokenDTO parseTokenForLogin(JwtTokenDTO token){

        Claims login = Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token.getToken()).getPayload();

        LoginFromTokenDTO loginL = new LoginFromTokenDTO();
        loginL.setLogin(login.getSubject());
        return loginL;
    }
}
