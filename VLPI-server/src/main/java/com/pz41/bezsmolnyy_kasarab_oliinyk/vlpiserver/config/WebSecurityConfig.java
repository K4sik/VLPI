package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.config;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.config.jwt.JWTTokenFilterConfigurer;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.config.jwt.JWTTokenProvider;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.enums.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final JWTTokenProvider jwtTokenProvider;

    public WebSecurityConfig(UserDetailsService userDetailsService, JWTTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**")
                .hasAnyAuthority(UserRole.ROLE_ADMIN.getAuthority(), UserRole.ROLE_STUDENT.getAuthority())
                .antMatchers(HttpMethod.POST, "/users/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
                .antMatchers(HttpMethod.PUT, "/users/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
                .antMatchers(HttpMethod.DELETE, "/users/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority());

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/tasks/**")
                .hasAnyAuthority(UserRole.ROLE_ADMIN.getAuthority(), UserRole.ROLE_STUDENT.getAuthority())
                .antMatchers(HttpMethod.POST, "/tasks/evaluation")
                .hasAnyAuthority(UserRole.ROLE_ADMIN.getAuthority(), UserRole.ROLE_STUDENT.getAuthority())
                .antMatchers(HttpMethod.POST, "/tasks/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
                .antMatchers(HttpMethod.PUT, "/tasks/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
                .antMatchers(HttpMethod.DELETE, "/tasks/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority());

        http.apply(new JWTTokenFilterConfigurer(jwtTokenProvider));
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
