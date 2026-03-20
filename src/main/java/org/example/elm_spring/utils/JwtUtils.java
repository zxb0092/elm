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

/**
 * JWT 工具类，提供 token 的生成与解析功能。
 * 使用 PBKDF2WithHmacSHA512 算法对原始密钥进行扩展，
 * 再以 HS256 算法对 JWT 进行签名，token 有效期为 30 分钟。
 */
@Component
public class JwtUtils {
    private String key = "zxb";
    private String extendedKey;

    /**
     * 构造方法。
     * 使用 PBKDF2WithHmacSHA512 算法将短密钥 {@code key} 扩展为 512 位的密钥，
     * 并将结果以 Base64 编码存储到 {@code extendedKey} 中，供后续签名/验签使用。
     */
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
     * 生成 JWT token。
     * 将传入的键值对写入 Claims，设置签发时间和 30 分钟过期时间，
     * 使用 HS256 算法和扩展密钥进行签名后返回紧凑格式的 token 字符串。
     *
     * @param map 需要写入 token 的自定义声明（如 userId、userName）
     * @return 生成的 JWT token 字符串
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
     * 解析 JWT token，获取其中的声明信息。
     * 使用扩展密钥对 token 进行验签，若 token 无效或已过期则抛出异常。
     *
     * @param token 待解析的 JWT token 字符串
     * @return token 中携带的 {@link Claims} 声明对象
     */
    public Claims parseToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(extendedKey)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
