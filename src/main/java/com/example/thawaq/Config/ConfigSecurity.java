package com.example.thawaq.Config;

import com.example.thawaq.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(getDaoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("api/v1/user/register-client/**").permitAll()
                .requestMatchers("api/v1/user/register-expert/**").permitAll()
                .requestMatchers("api/v1/user/register-store/**").permitAll()
                .requestMatchers("api/v1/user/get-all").hasAuthority("ADMIN")
                .requestMatchers("api/v1/branch/add/**").hasAuthority("STORE")
                .requestMatchers("/api/v1/category/get", "/api/v1/category/add", "/api/v1/category/update", "/api/v1/category/delete").permitAll()
                .requestMatchers("/api/v1/menu/get", "/api/v1/menu/add", "/api/v1/menu/update", "/api/v1/menu/delete").permitAll()

                .requestMatchers("/api/v1/favorite/add", "/api/v1/favorite/delete").permitAll()

                .requestMatchers("/api/v1/rating/average-rating-store","/api/v1/rating/average-rating-expert").permitAll()

                .requestMatchers("api/v1/user/block-expert","api/v1/user/unblock-expert").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/store/get-lowes-cost-cafes-name"
                        ,"/api/v1/store/get-lowes-cost-restaurant-name","/api/v1/store/get-lowes-cost-both-name",
                        "/api/v1/store/get-lowes-cost-cafes-category","/api/v1/store/get-lowes-restaurant-cafes-category",
                        "/api/v1/store/get-lowes-restaurant-both-category","/api/v1/store/get-lowes-cost-cafes-city",
                        "/api/v1/store/get-lowes-cost-restaurant-city", "/api/v1/store/get-lowes-cost-both-city").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }
}
