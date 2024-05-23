package com.work.ykserver.ykapps.config;

import com.work.ykserver.ykapps.constant.RequestConstants;
import com.work.ykserver.ykapps.config.filter.TokenVerifyFilter;
import com.work.ykserver.ykapps.config.handler.MyAuthenticationFailureHandler;
import com.work.ykserver.ykapps.config.handler.MyAuthenticationSuccessHandler;
import com.work.ykserver.ykapps.config.handler.MyLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

@Configuration
public class WebSecurityConfig {

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Resource
    private TokenVerifyFilter tokenVerifyFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> {
                    // 放行的请求路径
                    authorize.regexMatchers(RequestConstants.LOGIN_URI).permitAll()
                            .anyRequest().authenticated();
                })
                // 登录表单，完成登录请求
                .formLogin(formLogin -> {
                    formLogin.loginProcessingUrl(RequestConstants.LOGIN_URI)
                            .usernameParameter("loginAct")
                            .passwordParameter("loginPwd")
                            .successHandler(myAuthenticationSuccessHandler)
                            .failureHandler(myAuthenticationFailureHandler);
                })
                // 退出登录
                .logout(logout -> {
                   logout.logoutUrl(RequestConstants.LOGOUT_URI)
                           .logoutSuccessHandler(myLogoutSuccessHandler);
                })
                // 禁止跨站请求伪造
                .csrf(csrf -> {
                    csrf.disable();
                })
                /*.headers(header -> {
                    header.frameOptions().disable();
                })*/
                // 支持跨域
                .cors(cors -> {
                    cors.configurationSource(corsConfigurationSource);
                })
                // session 创建策略
                .sessionManagement(session -> {
                    // 无 session
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                // 添加自定义过滤器
                .addFilterBefore(tokenVerifyFilter, LogoutFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource () {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
