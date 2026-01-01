package com.gym.utils;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Jwt 工具类
 * 密码token相关
 *

 */
@Component
public class JwtUtil {

    private static long time = 1000*60*60*24;
    private static String signature = "admin";

    public static String createToken() {
        JwtBuilder jwtBuilder = Jwts.builder();

        String jwtToken =jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username","admin")
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();

        return jwtToken;
    }


    public static String createTokenToMember(String memberNo) {
        JwtBuilder jwtBuilder = Jwts.builder();

        String jwtToken = jwtBuilder
                // header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // payload —— 关键：加入 memberNo
                .claim("memberNo", memberNo)      // 👈 核心修改！
                .claim("role", "member")
                .setSubject("member-login")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                // signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();

        return jwtToken;
    }

    public static boolean checkToken(String token) {
        if (token == null) {
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
        }catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * 从 JWT Token 中解析出 memberNo
     *
     * @param token JWT 字符串
     * @return memberNo 字符串，若无效则返回 null
     */
    public static String getMemberNoFromToken(String token) {
        if (token == null) {
            return null;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(signature)
                    .parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            return body.get("memberNo", String.class); // 👈 从 payload 取 memberNo
        } catch (Exception e) {
            // 签名错误、过期、格式错误等都会抛异常
            return null;
        }
    }
}
