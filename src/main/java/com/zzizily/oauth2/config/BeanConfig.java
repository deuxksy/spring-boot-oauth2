package com.zzizily.oauth2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

  private final OAuth2ClientProperties oAuth2ClientProperties;
  @Value("${spring.application.name}")
  private String applicationName;
  private final DataSource dataSource;
  private final ResourceServerProperties resourceServerProperties;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  @Bean
  public ApprovalStore approvalStore() {
    return new JdbcApprovalStore(dataSource);
  }

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    jwtAccessTokenConverter.setSigningKey(resourceServerProperties.getJwt()
                                                                  .getKeyValue());
    return jwtAccessTokenConverter;
  }

  @Bean
  public CommonsRequestLoggingFilter requestLoggingFilter() {
    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
    loggingFilter.setIncludeHeaders(true);
    loggingFilter.setIncludeQueryString(true);
    loggingFilter.setIncludePayload(true);
    loggingFilter.setIncludeClientInfo(true);
    loggingFilter.setMaxPayloadLength(100);
//    loggingFilter.setBeforeMessagePrefix("BEFORE ===\n");
//    loggingFilter.setBeforeMessageSuffix("\n=== BEFORE");
//    loggingFilter.setAfterMessagePrefix("AFTER ===\n");
//    loggingFilter.setAfterMessageSuffix("\n=== AFTER");
    return loggingFilter;
  }

  @Bean
  @Primary
  public JdbcClientDetailsService jdbcClientDetailsService(DataSource dataSource) {
    return new JdbcClientDetailsService(dataSource);
  }

}
