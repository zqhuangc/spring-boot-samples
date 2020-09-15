package com.melody.opensource.springboot.security.utils;

import com.melody.opensource.springboot.security.config.SecurityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zqhuangc
 */
public class JwtTokenUtils {

    /**
     * 生成足够的安全随机密钥，以适合符合规范的签名
     */
    private static byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SecurityConstant.JWT_SECRET_KEY);

    private static SecretKey secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

    /**
     * 构造 token
     * @param username
     * @param roles
     * @param isRememberMe
     * @return
     */
    public static String createJwtToken(String username, List<String> roles, boolean isRememberMe) {
        long expiration = isRememberMe ? SecurityConstant.EXPIRATION_REMEMBER : SecurityConstant.EXPIRATION;
        // 构造 Jwt string
        String tokenPrefix = Jwts.builder()
                .setHeaderParam("typ", SecurityConstant.TOKEN_TYPE)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .claim(SecurityConstant.ROLE_CLAIMS, String.join(",", roles))
                .setIssuer("melody")
                .setIssuedAt(new Date())
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
        return SecurityConstant.TOKEN_PREFIX + tokenPrefix;
    }

    private boolean isTokenExpired(String token) {
        Date expiredDate = getJwtTokenBody(token).getExpiration();
        return expiredDate.before(new Date());
    }

    public static String getUserNameByToken(String token) {
        return getJwtTokenBody(token).getSubject();
    }

    /**
     * 获取用户所有角色
     * @param token
     * @return
     */
    public static List<SimpleGrantedAuthority> getUserRolesByToken(String token) {
        String role = (String) getJwtTokenBody(token)
                .get(SecurityConstant.ROLE_CLAIMS);
        return Arrays.stream(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * 解析 token 获取 Jwt string
     * @param token
     * @return
     */
    private static Claims getJwtTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token) // uncompact
                .getBody();
    }
}
