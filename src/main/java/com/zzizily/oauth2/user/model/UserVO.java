package com.zzizily.oauth2.user.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", schema = "oauth")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"username"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserVO {
  @Id
  private String username;
  private String password;
  private String fullName;
  private Boolean enabled;

  @OneToMany(targetEntity = UserRoleVO.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "username")
  private List<UserRoleVO> roles;

  @Builder
  public UserVO(String username, String password, String fullName, Boolean enabled, List<UserRoleVO> roles) {
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.enabled = enabled;
    this.roles = roles;
  }

  public List<String> getRolesToList() {
    List<String> list = new ArrayList<>();
    if (null != this.roles && !this.roles.isEmpty()) {
      for (UserRoleVO userRoleVO : this.roles) {
        list.add(userRoleVO.getRole());
      }
    }
    return list;
  }

  public String[] getRolesToArray() {
    List<String> list = this.getRolesToList();
    return list.toArray(new String[list.size()]);
  }
}
