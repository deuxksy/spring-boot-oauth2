package com.zzizily.oauth2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

  private final DataSource dataSource;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public TokenStore tokenStore() {
//    return new JwtTokenStore(jwtAccessTokenConverter());
    return new JdbcTokenStore(dataSource);
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
    jwtAccessTokenConverter.setSigningKey("tricycle");
    return jwtAccessTokenConverter;
  }

  @Bean
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
  }

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
