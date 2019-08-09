package com.zzizily.oauth2.config;

import com.zzizily.oauth2.user.model.UserRoleVO;
import com.zzizily.oauth2.user.model.UserVO;
import com.zzizily.oauth2.user.repository.UserRepository;
import com.zzizily.oauth2.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.ContextIdApplicationContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CommandConfig implements CommandLineRunner {

  @Value("${spring.application.name}")
  private String applicationName;

  @Value("${spring.profiles.active}")
  private String profiles;

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;

  @Override
  public void run(String... args) throws Exception {
    log.debug("{}, {}", applicationName, profiles);

    UserVO kadmin = userRepository.save(UserVO.builder().username("admin").password("{noop}password").fullName("김관리자").enabled(Boolean.TRUE).build());
    UserVO kuser =  userRepository.save(UserVO.builder().username("user").password("{noop}password").fullName("김사용자").enabled(Boolean.TRUE).build());
    userRoleRepository.save(UserRoleVO.builder().username(kadmin.getUsername()).role("ADMIN").build());
    userRoleRepository.save(UserRoleVO.builder().username(kuser.getUsername()).role("USER").build());
  }
}

