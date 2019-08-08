package com.zzizily.oauth2.user.service;

import com.zzizily.oauth2.user.model.UserVO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
  List<UserVO> findAll();

  UserVO save(UserVO user);

  UserVO findById(String username);

  void deleteById(String username);
}

