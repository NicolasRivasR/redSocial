package com.nicolas.redSocial.configuration;

import com.nicolas.redSocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserService userservice;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService detailsService){
        
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(detailsService);
        return new ProviderManager(daoAuthenticationProvider);
        
    }*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {

                    registry.requestMatchers("/home", "/register/user").permitAll();
                    registry.requestMatchers("/admin/**").hasAnyRole("ADMIN");
                    registry.requestMatchers("/user/**").hasRole("USER");
                    registry.anyRequest().authenticated();

                })
                .formLogin(fromlogin -> fromlogin.permitAll())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userservice;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /*
    @Bean
    public UserDetailsService userDetailsService(){
        
        UserDetails nomralUser = User.builder()
                .username("usuariobasico")
                .password("$2a$12$OB9tBexm22MaDzpLHRo5tu8JVjEmtAiY5r.vBeLkK3TPNSYcmsYHS") //-> se tiene que oculatar la contraseña
                .roles("USER")
                .build();
        
        UserDetails adminUser = User.builder()
                .username("usuarioadmin")
                .password("$2a$12$OB9tBexm22MaDzpLHRo5tu8JVjEmtAiY5r.vBeLkK3TPNSYcmsYHS") //-> se tiene que oculatar la contraseña
                .roles("ADMIN", "USER")
                .build();
        
        return new InMemoryUserDetailsManager( nomralUser,adminUser);
    }*/
}