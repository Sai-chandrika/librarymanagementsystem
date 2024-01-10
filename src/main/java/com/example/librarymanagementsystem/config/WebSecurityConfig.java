package com.example.librarymanagementsystem.config;

import com.example.librarymanagementsystem.customexception.AccessDeniedException;
import com.example.librarymanagementsystem.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 10/01/24
 * @Time ➤➤➤ 11:26 am
 * @Project ➤➤➤ librarymanagementsystem
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private AppUserRepo appUserRepo;

    @Value("${appUser.login.type}")
    private String loginTypeValue;

    @Value("${login.expiration.time.in.minutes}")
    private Integer tokenExpirationTime;

    private final String[] PUBLIC_RESOURCE_AND_URL={"/",


    };

    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a-> a.anyRequest().authenticated())
                .exceptionHandling(e-> e.accessDeniedHandler(accessDeniedHandler()))
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtils,appUserRepo,loginTypeValue,tokenExpirationTime), BasicAuthenticationFilter.class)
                .addFilterBefore(new CustomCORSFilter(), ChannelProcessingFilter.class);
        return http.build();
    }

    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDenieHandler();
    }
}
