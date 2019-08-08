package com.zzizily.oauth2.user.model;

//@Setter
//@Getter
//@ToString
//@EqualsAndHashCode
public class CustomUserDetails /*extends User */{

  /*private String fullName;

  private CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }

  private CustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
  }

  public CustomUserDetails(UserVO userVO) {
    this(userVO.getUsername(), userVO.getPassword(), User.builder()
                                                         .roles(userVO.getRolesToArray())
                                                         .build()
                                                         .getAuthorities());
    this.fullName = userVO.getFullName();
  }*/
}