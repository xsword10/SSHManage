package cn.xsword.sshmanage.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-09 17:40
 * @description: 实现基于JWT的认证和授权的工具
 **/
public class JwtUtil {
    public static final long JWT_TTL = 60 * 60 * 1000L * 24 * 1; // 有效期1天
    public static final String JWT_KEY = "SDFGjhdsfalshdfHFdsjkdsfds121232131afasdfac";

    /**
     * @Description: 生成uuid,用作jwt的id，确保jwt id 唯一
     * @Author: xsword
     * @Date: 2025/7/9
    **/
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @Description: 调用getJwtBuilder方法生成一个jwt构建器，并将其压缩为字符串返回
     * @Author: xsword
     * @Date: 2025/7/9
    **/
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    /**
     * @Description: jwt构建器
     * @Author: xsword
     * @Date: 2025/7/9
    **/
    public static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        return Jwts.builder()
                .id(uuid)
                .subject(subject)
                .issuer("sshmanage")
                .issuedAt(now)
                .signWith(secretKey)
                .expiration(exp);
    }

    /**
     * @Description: 生成密钥
     * @Author: xsword
     * @Date: 2025/7/9
    **/
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }

    /**
     * @Description: 解析jwt
     * @Author: xsword
     * @Date: 2025/7/9
    **/
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
