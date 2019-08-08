package com.zzizily.oauth2.user.service;

import com.zzizily.oauth2.user.model.UserVO;
import com.zzizily.oauth2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public List<UserVO> findAll() {
    return userRepository.findAll();
  }

  @Override
  public UserVO findById(String username) {
    return userRepository.findById(username)
                         .get();
  }

  @Override
  public void deleteById(String username) {
    userRepository.deleteById(username);
  }

  @Override
  public UserVO save(UserVO user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserVO user = findById(username);
    log.trace("{}", Strings.join(user.getRolesToList()
                                     .iterator(), ','));
    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException(String.format("사용자명 %s 을(를) 찾을수 없습니다.", username));
    }
    return User.builder()
               .username(user.getUsername())
               .password(user.getPassword())
               .roles(user.getRolesToArray())
               .build();
  }
}