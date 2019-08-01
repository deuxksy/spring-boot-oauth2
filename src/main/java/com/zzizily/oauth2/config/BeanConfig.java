package com.zzizily.oauth2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

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
  public ResourceServerTokenServices remoteTokenServices() {
    DefaultTokenServices tokenServices = new DefaultTokenServices();
    tokenServices.setSupportRefreshToken(true);
    tokenServices.setTokenStore(new JdbcTokenStore(dataSource));
    return tokenServices;
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
    loggingFilter.setBeforeMessagePrefix("BEFORE ===\n");
    loggingFilter.setBeforeMessageSuffix("\n=== BEFORE");
    loggingFilter.setAfterMessagePrefix("AFTER ===\n");
    loggingFilter.setAfterMessageSuffix("\n=== AFTER");
    return loggingFilter;
  }

  /*@Bean
  public WebMvcConfigurer webMvcConfigurer() {
    List<String> allowedOrigins = corsEndpointProperties.getAllowedOrigins();
    List<String> allowedHeaders = corsEndpointProperties.getAllowedHeaders();
    List<String> allowedMethods = corsEndpointProperties.getAllowedMethods();
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
          .addMapping("/**")
          .allowedHeaders(allowedHeaders.toArray(new String[allowedHeaders.size()]))
          .allowedMethods(allowedMethods.toArray(new String[allowedMethods.size()]))
          .allowedOrigins("*")
          .allowCredentials(corsEndpointProperties.getAllowCredentials())
          .maxAge(corsEndpointProperties.getMaxAge().getSeconds())
        ;
      }
    };
  }*/

  @Bean
  @Primary
  public JdbcClientDetailsService jdbcClientDetailsService(DataSource dataSource) {
    return new JdbcClientDetailsService(dataSource);
  }

}
