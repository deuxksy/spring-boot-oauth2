package com.zzizily.oauth2.user.service;

import com.zzizily.oauth2.user.model.OauthUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OauthUserService extends UserDetailsService {
  List<OauthUser> findAll();

  OauthUser save(OauthUser user);

  OauthUser getByUsername(String username);

  void delete(Long id);

  OauthUser findByUsernameAndSiteId(String username);

  int getSiteId();

  void setSiteId(int siteId);
}

