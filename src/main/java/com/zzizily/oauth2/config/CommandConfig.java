package com.zzizily.oauth2.config;

import com.zzizily.oauth2.user.model.OauthUser;
import com.zzizily.oauth2.user.model.OauthUserRole;
import com.zzizily.oauth2.user.repository.OauthUserRepository;
import com.zzizily.oauth2.user.repository.OauthUserRoleRepository;
import com.zzizily.oauth2.user.service.OauthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CommandConfig implements CommandLineRunner {
  @Value("${spring.profiles.active}")
  private String profiles;

  @Override
  public void run(String... args) throws Exception {
    log.debug("{}", profiles);

  }

  @Bean
  public CommandLineRunner initData(OauthUserRepository oauthUserRepository, OauthUserRoleRepository oauthUserRoleRepository) {
    return (args) -> {
//      oauthUserRoleRepository.save(OauthUserRole.builder().oauthUserId(1L).role("ROLE_USER").build());
//      oauthUserRoleRepository.save(OauthUserRole.builder().oauthUserId(1L).role("ROLE_ADMIN").build());

//      oauthUserRepository.save(OauthUser.builder().username("user").password("{noop}password").siteId(1).useYn("Y").build());
//      oauthUserRepository.save(OauthUser.builder().username("admin").password("{noop}password").siteId(1).useYn("Y").build());
    };
  }
}

