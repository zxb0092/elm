package org.example.elm_spring.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    private String key = "zxb";
    private String extendedKey;

    public JwtUtils() {
        try {
            // 使用PBKDF2算法扩展密钥
            PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), "salt".getBytes(), 10000, 512);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            extendedKey = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成token
     */
    public String createToken(Map<String, Object> map) {
        String token = Jwts.builder()
                .setClaims(map)
                // 颁发时间
                .setIssuedAt(new Date())
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                // 加密算法
                .signWith(SignatureAlgorithm.HS256, extendedKey)
                .compact();
        return token;
    }

    /**
     * 解析token
     */
    public Claims parseToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(extendedKey)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
