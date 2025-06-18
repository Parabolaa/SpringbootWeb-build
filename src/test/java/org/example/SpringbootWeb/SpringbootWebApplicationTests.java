package org.example.SpringbootWeb;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testGenJwt() {
        // 生成密钥
        Key key = Keys.hmacShaKeyFor("a/very/secure/and/long/secret/key/123456789123456789".getBytes()); // 至少 32 字节
        // 自定义payload
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "alice");
        String jwt = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256) //设置签名算法和签名本身
                .setClaims(claims) //自定义内容,payload
                .setExpiration(new Date(System.currentTimeMillis() + 100*1000)) //有效期为1s
                .compact();
        System.out.println(jwt);
    }

//    @Test
//    public void testParseJwt() {
//        String secret = "a/very/secure/and/long/secret/key/123456789123456789";
//        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//
//        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiYWxpY2UiLCJpZCI6MSwiZXhwIjoxNzQ5NTkzMTk3fQ._TjP4rUR6Vx9-BIbgIQ7EHPhrSrJN1b_-56CGsaRHXo";
//
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(jwt)
//                .getBody();
//
//        System.out.println(claims);
//    }

}
