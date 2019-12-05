package com.rex.springboot.utils;

import com.auth0.jwt.JWT;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rex.springboot.bean.User;
import org.apache.tomcat.util.security.MD5Encoder;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class JwtToken {
    public static String SECRET = "demo";

    public static String createToken () throws UnsupportedEncodingException {
        Date iatDate = new Date();

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 1);
        Date expireTime = nowTime.getTime();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create().withHeader(map)
                .withClaim("name", "rex")
                .withClaim("age", "22")
                .withClaim("sex", "1")
                .withExpiresAt(expireTime)
                .withIssuedAt(iatDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public static Map<String, Claim> verifyToken (String token) throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("登录凭证过期，请重新登陆！");
        }
        return jwt.getClaims();
    }

    public static void main(String[] args) throws Exception {
        String token = createToken();
        System.out.println(token);
        Map<String, Claim> claims = JwtToken.verifyToken(token);
        System.out.println(claims.get("name").asString());


    }

//    public String getCity(User user) throws Exception{
//        return Optional.ofNullable(user)
//                .map(u-> u.getName())
//                .map(a->a.getCity())
//                .orElseThrow(()->new Exception("取指错误"));
//    }
}
