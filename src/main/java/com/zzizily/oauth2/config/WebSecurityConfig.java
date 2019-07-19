package com.zzizily.oauth2.config;

import com.zzizily.oauth2.user.service.OauthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final DataSource dataSource;
  private final PasswordEncoder passwordEncoder;
  private final OauthUserService oauthUserService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .headers().frameOptions().disable()
      .and()
        .cors().disable()
        .csrf().disable()
      .authorizeRequests()
        .antMatchers("/", "/h2-console/**","/actuator/**").permitAll()
        // .antMatchers("/api/session").hasAuthority("ROLE_ADMIN") == .antMatchers("/api/session").hasRole("ADMIN")
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/user").hasRole("USER")
        .antMatchers("/me").hasAnyRole("USER","ADMIN")
        .anyRequest().permitAll()
    ;
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(oauthUserService)
      .passwordEncoder(passwordEncoder)
    ;

  }

}
