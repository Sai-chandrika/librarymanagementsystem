package com.example.librarymanagementsystem.config;

import com.example.librarymanagementsystem.customexception.NotFoundException;
import com.example.librarymanagementsystem.entity.AppUser;
import com.example.librarymanagementsystem.utility.GenericAppValidator;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWEDecryptionKeySelector;
import com.nimbusds.jose.proc.JWEKeySelector;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 10/01/24
 * @Time ➤➤➤ 9:52 am
 * @Project ➤➤➤ librarymanagementsystem
 */

@Service
public class JwtTokenUtils {

    public static String secretkey = "841D8A6C80CBA4FCAD32D5367C18C53B";

    private static final long serialVersionUID = -1029281748694725202L;

    @Value("${appUser.login.type}")
    private String loginTypeValue;

    @Value("${login.expiration.time.in.minutes}")
    private Integer expirationTime;

    public String getToken(AppUser appUser) throws JOSEException {

        JWTClaimsSet.Builder claims = new JWTClaimsSet.Builder();
        claims.expirationTime(new Date(new Date().getTime() + (expirationTime - 1L) * 60 * 1000)); //long format  add expire time
        String errorMessage = "Requested Credentials not found as per application properties";
        switch (loginTypeValue) {
            case "EMAIL" -> {
                if (Boolean.TRUE.equals(GenericAppValidator.isNotNull(appUser.getEmail()))) {
                    claims.claim("email", appUser.getEmail()).build();
                } else throw new NotFoundException(errorMessage);
            }
            case "USERNAME" -> {
                if (Boolean.TRUE.equals(GenericAppValidator.isNotNull(appUser.getUserName()))) {
                    claims.claim("userName", appUser.getUserName()).build();
                } else throw new NotFoundException(errorMessage);
            }
            case "CONTACTNUMBER" -> {
                if (Boolean.TRUE.equals(GenericAppValidator.isNotNull(appUser.getContactNumber()))) {
                    claims.claim("contactNumber", appUser.getContactNumber()).build();
                } else throw new NotFoundException(errorMessage);
            }
        }
        claims.claim("createOn", String.valueOf(LocalDateTime.now())).build();

        Payload payload = new Payload(claims.build().toJSONObject());

        JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256);
        DirectEncrypter encrypter = new DirectEncrypter(secretkey.getBytes(StandardCharsets.UTF_8));
        JWEObject jweObject = new JWEObject(header, payload);
        jweObject.encrypt(encrypter);
        String token = jweObject.serialize();
        return token;

    }

    public String parseToken(String token) throws BadJOSEException, ParseException, JOSEException {
        ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor = new DefaultJWTProcessor<SimpleSecurityContext>();
        JWKSource<SimpleSecurityContext> jweKeySource = new ImmutableSecret<>(secretkey.getBytes());
        JWEKeySelector<SimpleSecurityContext> jweKeySelector = new JWEDecryptionKeySelector<SimpleSecurityContext>(JWEAlgorithm.DIR,EncryptionMethod.A128CBC_HS256,jweKeySource);

        jwtProcessor.setJWEKeySelector(jweKeySelector);

        JWTClaimsSet claims = jwtProcessor.process(token, null);
        String userName = null;
        switch (loginTypeValue){
            case "EMAIL" ->
                    userName = (String) claims.getClaim("email");
            case "USERNAME" ->
                userName = (String) claims.getClaim("userName");
            case "CONTACTNUMBER" ->
                userName = (String) claims.getClaim("contactNumber");
         }
           // DynamicTokenStore.tokenCreationTime = LocalDateTime.parse((String)claims.getClaim("createdOn"));
         return userName;
    }
}

