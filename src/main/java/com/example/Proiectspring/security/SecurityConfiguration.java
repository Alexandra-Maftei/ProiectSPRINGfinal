package com.example.Proiectspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/bootstrap/**").permitAll()
                        .requestMatchers(toH2Console()).permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/teamdata").permitAll()
                        .requestMatchers("/registration/**").permitAll()
                        .requestMatchers("/index").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/PlayerOverview").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/PlayerForm").hasAnyRole( "ADMIN")
                        .requestMatchers("/submitPlayer").hasAnyRole( "ADMIN")
                        .requestMatchers("/editPlayer/{id}").hasAnyRole( "ADMIN")
                        .requestMatchers("/updatePlayer").hasAnyRole( "ADMIN")
                        .requestMatchers("/deletePlayer/{id}").hasAnyRole("ADMIN")
                        .requestMatchers("/view").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/TeamOverview").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/TeamForm").hasAnyRole( "ADMIN")
                        .requestMatchers("/submitTeam").hasAnyRole( "ADMIN")
                        .requestMatchers("/editTeam/{id}").hasAnyRole( "ADMIN")
                        .requestMatchers("/updateTeam").hasAnyRole( "ADMIN")
                        .requestMatchers("/deleteTeam/{id}").hasAnyRole( "ADMIN")
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().disable())
                .csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()))
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/index")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling().accessDeniedPage("/access-denied");
        return http.build();
    }
}
