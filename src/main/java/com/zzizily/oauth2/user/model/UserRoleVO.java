package com.zzizily.oauth2.user.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities", schema = "oauth")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"username", "role"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoleVO {
  @Id
  private String username;

  @Column(name = "authority")
  private String role;

  @Builder
  public UserRoleVO(String username, String role) {
    this.username = username;
    this.role = role;
  }
}
