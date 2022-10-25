package com.ynhj.nativemysql.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.List;

public class JwtUtil {
    private static Key key;
    public static final String APPSECRET_KEY = "123456123456123456123456123456123456123456123";

    static {
        key = Keys.hmacShaKeyFor(APPSECRET_KEY.getBytes());
    }


    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String SUBJECT = "yong1";

    public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 1;

    private static final String ROLE_CLAIMS = "rol";


    /**
     * 生成token
     *
     * @param username
     * @param role
     * @return
     */
    public static String createToken(Long id, String username, List<String> role) {

        String token = Jwts
                .builder()
                .setSubject(username)
                .claim("id", id)
                .claim("username", username)
                .claim(ROLE_CLAIMS, role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(key)
                .compact();

        return token;
    }

    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.get("username").toString();
    }

    /**
     * 获取用户角色
     *
     * @param token
     * @return
     */
    public static String getUserRole(String token) {
        Claims claims = getClaims(token);
        return claims.get("rol").toString();
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date());
    }

    private static Claims getClaims(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims;
    }

    public static Boolean validateToken(String token) {
        return !isExpiration(token);
    }


}

