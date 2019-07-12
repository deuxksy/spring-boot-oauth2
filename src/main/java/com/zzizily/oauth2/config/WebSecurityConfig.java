package com.zzizily.oauth2.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .headers().frameOptions().disable()
      .and().cors().disable().csrf().disable()
      .authorizeRequests()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/actuator/**").permitAll()
        .antMatchers("/").permitAll()
        .anyRequest().authenticated()
      .and().formLogin()
        .permitAll()
      .and().logout().permitAll()
      .and().httpBasic()

    ;
  }

  @Bean
  public PasswordEncoder encoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }


  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
//      .jdbcAuthentication()
      .jdbcAuthentication()
//      .withUser("user").password("{noop}password").roles("USER").and()
//      .withUser("admin").password("{noop}password").roles("ADMIN")
    ;

  }

}
