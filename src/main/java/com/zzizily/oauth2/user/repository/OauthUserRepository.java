package com.zzizily.oauth2.user.repository;

import com.zzizily.oauth2.user.model.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthUserRepository extends JpaRepository<OauthUser, Long> {
  OauthUser findByUsername(String username);

  OauthUser findByUsernameAndSiteId(String username, int siteId);
}
