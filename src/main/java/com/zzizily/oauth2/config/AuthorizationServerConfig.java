package com.zzizily.oauth2.config;

import com.zzizily.oauth2.user.service.OauthUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  private final AuthenticationManager authenticationManager;
  private final DataSource dataSource;
  private final TokenStore tokenStore;
  private final ApprovalStore approvalStore;
  private final PasswordEncoder passwordEncoder;
  private final JwtAccessTokenConverter jwtAccessTokenConverter;
  private final ClientDetailsService clientDetailsService;
  private final OauthUserService oauthUserService;

  public AuthorizationServerConfig(AuthenticationConfiguration authenticationConfiguration, DataSource dataSource, TokenStore tokenStore, ApprovalStore approvalStore, PasswordEncoder passwordEncoder, JwtAccessTokenConverter jwtAccessTokenConverter, ClientDetailsService clientDetailsService, OauthUserService oauthUserService) throws Exception {
    this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
    this.dataSource = dataSource;
    this.tokenStore = tokenStore;
    this.approvalStore = approvalStore;
    this.passwordEncoder = passwordEncoder;
    this.jwtAccessTokenConverter = jwtAccessTokenConverter;
    this.clientDetailsService = clientDetailsService;
    this.oauthUserService = oauthUserService;
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

    super.configure(endpoints);

    endpoints
      .accessTokenConverter(jwtAccessTokenConverter)
      .authenticationManager(authenticationManager)
      .approvalStore(approvalStore)
//      .tokenStore(tokenStore)
//      .userDetailsService(oauthUserService)
    ;
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
      .jdbc(dataSource)
      .passwordEncoder(passwordEncoder)
    ;
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer
      .tokenKeyAccess("permitAll()")
      .checkTokenAccess("isAuthenticated()")
    ;
  }
}