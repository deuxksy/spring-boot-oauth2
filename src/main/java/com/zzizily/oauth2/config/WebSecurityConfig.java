package com.zzizily.oauth2.config;

import com.zzizily.oauth2.user.service.OauthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
      .headers().frameOptions().disable().and()
      .cors().disable()
      .csrf().disable()
      .formLogin().and()
      .httpBasic()
    ;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
      .antMatchers(HttpMethod.OPTIONS, "/**")
      .antMatchers("/h2-console/**")
      .antMatchers("/swagger-ui/index.html")
    ;
  }

  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(oauthUserService)
      .passwordEncoder(passwordEncoder)
    ;

  }

}
