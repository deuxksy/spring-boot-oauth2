package com.zzizily.oauth2.config;

import com.zzizily.oauth2.user.model.OauthUser;
import com.zzizily.oauth2.user.model.OauthUserRole;
import com.zzizily.oauth2.user.repository.OauthUserRepository;
import com.zzizily.oauth2.user.repository.OauthUserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CommandConfig implements CommandLineRunner {

  @Value("${spring.application.name}")
  private String applicationName;

  @Value("${spring.profiles.active}")
  private String profiles;

  private final OauthUserRepository oauthUserRepository;
  private final OauthUserRoleRepository oauthUserRoleRepository;

  @Override
  public void run(String... args) throws Exception {
    log.debug("{}-{}", applicationName, profiles);
    oauthUserRepository.save(OauthUser.builder().username("user").password("{noop}password").siteId(1).useYn("Y").build());
    oauthUserRepository.save(OauthUser.builder().username("admin").password("{noop}password").siteId(1).useYn("Y").build());
    oauthUserRoleRepository.save(OauthUserRole.builder().role("ROLE_USER").build());
    oauthUserRoleRepository.save(OauthUserRole.builder().role("ROLE_ADMIN").build());
  }
}

