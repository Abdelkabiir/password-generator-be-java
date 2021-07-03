package PasswordManager.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class TokensUtil {

    public String createToken() {
        long nowMillis = System.currentTimeMillis();
        long afterOneDay = 86400000;

        Date now = new Date(nowMillis);
        Date tomorrow = new Date(nowMillis + afterOneDay);

        Algorithm algorithm = Algorithm.HMAC256("secret");

        String token = JWT.create()
                .withIssuer("Abdel")
                .withIssuedAt(now)
                .withExpiresAt(tomorrow)
                .sign(algorithm);

        return token;
    }

    public Boolean validateTokenExpiration(String token) {
        DecodedJWT jwt = JWT.decode(token);

        Date expiresAt = jwt.getExpiresAt();

        long nowMillis = System.currentTimeMillis();

        return  (nowMillis < expiresAt.getTime());
    }
}
