package com.ramon.pereira.albumstore.security.config;

import com.ramon.pereira.albumstore.security.filter.JwtAuthenticationFilter;
import com.ramon.pereira.albumstore.security.handler.CustomAuthenticationEntryPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(
        super.userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Configuration
  @Order(1)
  public static class BasicSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Override
    public void configure(final WebSecurity web) {
      web.ignoring()
          .antMatchers(
              "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**");
    }

    protected void configure(final HttpSecurity http) throws Exception {
      http
          .requestMatchers()
          .antMatchers("/actuator/**")
          .and()
          .authorizeRequests()
          .anyRequest()
          .authenticated()
          .and()
          .httpBasic()
          .and()
          .exceptionHandling()
          .authenticationEntryPoint(this.customAuthenticationEntryPoint)
          .and()
          .csrf()
          .disable();
    }
  }

  @Configuration
  @Order(2)
  public static class ApiTokenSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
      http
          .headers()
          .frameOptions()
          .disable()
          .and()
          .authorizeRequests()
          .antMatchers("/**")
          .authenticated()
          .and()
          .addFilter(new JwtAuthenticationFilter(super.authenticationManager(), jwtSecretKey))
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .exceptionHandling().authenticationEntryPoint(this.customAuthenticationEntryPoint)
          .and()
          .csrf()
          .disable();
    }
  }
}