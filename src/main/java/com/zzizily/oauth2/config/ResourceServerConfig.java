package com.zzizily.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@Profile("!stage")
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {

    http
      .authorizeRequests()
        .antMatchers("/", "/h2-console/**","/actuator/**", "/management/health").permitAll()
        .antMatchers(org.springframework.http.HttpMethod.OPTIONS, "/oauth/token").permitAll()
        .anyRequest().authenticated().and()
      .requestMatchers()
      .antMatchers("/admin")
      .antMatchers("/user")
      .antMatchers("/me")
    ;
  }

  /*@Override
  public void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .anyRequest().authenticated()
      .and().requestMatchers()
        .antMatchers("/api/**")
    ;
  }*/
}
