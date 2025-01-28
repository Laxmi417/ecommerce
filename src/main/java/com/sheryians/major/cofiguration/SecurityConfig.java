package com.sheryians.major.cofiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.sheryians.major.service.CustomUserDetailService;

@Configuration
public class SecurityConfig {

     AuthenticationSuccessHandler googleOAuth2SuccessHandler;
    CustomUserDetailService customUserDetailService;

    public SecurityConfig(AuthenticationSuccessHandler googleOAuth2SuccessHandler, CustomUserDetailService customUserDetailService) {
        this.googleOAuth2SuccessHandler = googleOAuth2SuccessHandler;
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorize -> authorize
                //antMatchers("/resources/**", "/static/**", "/images/**", "/productImages/**", "/css/**", "/js/**").permitAll()
                .antMatchers("/", "/shop/**", "/register", "/h2-console/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password"))
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .successHandler(googleOAuth2SuccessHandler))
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSI2qONID"))
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions().disable());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    
}
