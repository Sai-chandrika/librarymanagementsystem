package com.example.librarymanagementsystem.config;

import com.example.librarymanagementsystem.customexception.InvalidDataException;
import com.example.librarymanagementsystem.dto.GenericResponse;
import com.example.librarymanagementsystem.entity.AppUser;
import com.example.librarymanagementsystem.repo.AppUserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 10/01/24
 * @Time ➤➤➤ 10:28 am
 * @Project ➤➤➤ librarymanagementsystem
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;
    private final AppUserRepo appUserRepo;

    private final String loginTypeValue;

    private final Integer tokenExpirationTime;
    private final Integer thresholdExpirationTime = 2;

    JwtAuthenticationFilter(JwtTokenUtils jwtTokenUtils, AppUserRepo appUserRepo, String loginTypeValue, Integer tokenExpirationTime) {

        this.jwtTokenUtils = jwtTokenUtils;
        this.appUserRepo = appUserRepo;
        this.loginTypeValue = loginTypeValue;
        this.tokenExpirationTime = tokenExpirationTime;
    }

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authToken = extractAuthToken(request.getHeader("Authorization"));

            String userName = jwtTokenUtils.parseToken(authToken);
            AppUser appUser = validateUserNameType(userName);
            if (appUser == null)
                generateUnauthorisedAccess(response);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(appUser,null, Arrays.asList(
                  new SimpleGrantedAuthority(appUser.getRoleType().name())));

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            logger.info("authenticated user "+userName+ ", setting security context");

            SecurityContextHolder.getContext().setAuthentication(authentication);
            LocalDateTime tokenTime = DynamicTokenStore.tokenCreationTime;
            if (tokenTime.plusMinutes((long) tokenExpirationTime-thresholdExpirationTime).isBefore(LocalDateTime.now())){
                response.setHeader("Authorization",jwtTokenUtils.getToken(appUser));
            }
            filterChain.doFilter(request,response);

        } catch (Exception e) {
            logger.info(e.getMessage());
            generateUnauthorisedAccess(response);
        }
    }

    private String extractAuthToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    public void generateUnauthorisedAccess(HttpServletResponse res) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        GenericResponse response = new GenericResponse(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORZATION");
        String jsonResString = ow.writeValueAsString(response);
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = res.getWriter();
        writer.write(jsonResString);
        logger.info("=======================================");
    }

    private AppUser validateUserNameType(String userName) {
        AppUser appUser;
        switch (loginTypeValue) {
            case "EMAIL" -> appUser = appUserRepo.findByEmail(userName);
            case "USERNAME" -> appUser = appUserRepo.findByUserName(userName);
            case "CONTACTNUMBER" -> appUser = appUserRepo.findByContactNumber(userName);
            default -> throw new InvalidDataException("invalid data of login type");
        }
        return appUser;
    }


}
