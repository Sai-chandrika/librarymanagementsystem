package com.example.librarymanagementsystem.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 10/01/24
 * @Time ➤➤➤ 9:44 am
 * @Project ➤➤➤ librarymanagementsystem
 */
public class CustomCORSFilter extends OncePerRequestFilter {

    CustomCORSFilter() {

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");  //Allow any domain
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS"); //Allow methods
        response.setHeader("Access-Control-Max-Age", "3600");  //time in ms
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-type, xsrf-token"); //headers as jey
        response.setHeader("Access-Control-Expose-Headers", "xsrf-token");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
