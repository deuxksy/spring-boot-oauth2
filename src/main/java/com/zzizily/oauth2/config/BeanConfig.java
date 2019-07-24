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
//    return new JdbcTokenStore(dataSource);
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
    jwtAccessTokenConverter.setSigningKey(resourceServerProperties.getJwt().getKeyValue());
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

  @Bean
  @Primary
  public JdbcClientDetailsService jdbcClientDetailsService(DataSource dataSource) {
    // Jdbc(H2 데이터베이스)를 이용한 Oauth client 정보등록을 위한 설정입니다.
    return new JdbcClientDetailsService(dataSource);
  }


/*  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration();

//    corsConfiguration.addAllowedOrigin("*");
//    corsConfiguration.addAllowedMethod("*");
//    corsConfiguration.addAllowedHeader("*");
//    corsConfiguration.setAllowCredentials(true);
//    corsConfiguration.setMaxAge(3600L);

    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
    return urlBasedCorsConfigurationSource;
  }*/

  /**
   * CorsConfigurationSource 적용 되지 않아 SimpleCorsFilter 로 사용
   */
  /*
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    //CORS 요청을 허용할 사이트 (e.g. https://local.trcc.com)
    List<String> allowedOrigins = new ArrayList();
    allowedOrigins.add("https://localss.trcc.com:7443");
    allowedOrigins.add("https://local.trcc.com:8443");
    allowedOrigins.add("https://local.trcc.com:9443");
    allowedOrigins.add("https://client-skeleton-dev-service-pig.trcc.com:8443");
    allowedOrigins.add("https://api-skeleton-dev-service-pig.trcc.com:8443");

    configuration.setAllowedOrigins(allowedOrigins);
    //CORS 요청을 허용할 Http Method들 (e.g. GET,PUT,POST)
    configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
    //특정 헤더를 가진 경우에만 CORS 요청을 허용할 경우
    configuration.setAllowedHeaders(Arrays.asList("x-requested-with","content-type", "authorization"));
    //자격증명과 함께 요청을 할 수 있는지 여부.
    //해당 서버에서 Authorization로 사용자 인증도 서비스할 것이라면 true로 응답해야 할 것이다
    configuration.setAllowCredentials(true);
    //preflight 요청의 캐시 시간.
    //configuration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
  */

}
