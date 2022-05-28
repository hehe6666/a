package com.xuan.practice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xuan.practice.pojo.User;
import java.util.Calendar;
import java.util.Date;

public class JwtUtils {
    //生成jwt
    public static String createToken(User user ,String secret){

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR,3);
        Date expiresDate = nowTime.getTime();

        return JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(expiresDate)
                .withClaim("id",user.getId())
                .sign(Algorithm.HMAC256(secret));
    }

    //解释jwt，验证其是否被修改过
    public static boolean verifyToken(String token,String secret){
        DecodedJWT decodedJWT= null;
        try{
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            decodedJWT = jwtVerifier.verify(token);
            return true;
        }catch (Exception e){
            throw e;
        }
    }

    public static Claim getClaimByName(String token,String name){
        return JWT.decode(token).getClaim(name);
    }
}
