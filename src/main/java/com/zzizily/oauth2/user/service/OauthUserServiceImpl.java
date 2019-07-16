package com.zzizily.oauth2.user.service;

import com.zzizily.oauth2.user.model.OauthUser;
import com.zzizily.oauth2.user.model.OauthUserRole;
import com.zzizily.oauth2.user.repository.OauthUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthUserServiceImpl implements OauthUserService {
  private final OauthUserRepository oauthUserRepository;
  public int siteId = 1; //하프 : 1, 보리 : 2

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public List<OauthUser> findAll() {
    return oauthUserRepository.findAll();
  }

//  @Override
//  public OauthUser getByUsername(String username) {
//    return oauthUserRepository.findByUsername(username);
//  }

  @Override
  public OauthUser save(OauthUser user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setSiteId(this.siteId);
    user.setUseYn("Y");
    return oauthUserRepository.save(user);
  }

  @Override
  public void delete(Long id) {
    oauthUserRepository.deleteById(id);
  }

  @Override
  public int getSiteId() {
    return this.siteId;
  }

  @Override
  public void setSiteId(int siteId) {
    this.siteId = siteId;
  }

  @Override
  public OauthUser findByUsernameAndSiteId(String username) {
    return oauthUserRepository.findByUsernameAndSiteId(username, this.siteId);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    OauthUser user = oauthUserRepository.findByUsernameAndSiteId(username, this.siteId);
    if (user == null) {
      throw new UsernameNotFoundException("UsernameNotFound");
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(List<OauthUserRole> roles) {
    List<GrantedAuthority> list = new ArrayList<>(1);

    if (roles != null) {
      roles.stream()
           .forEach((OauthUserRole role) ->
           {
             list.add(new SimpleGrantedAuthority(role.getRole()));
           });
    }
    return list;
  }
}
