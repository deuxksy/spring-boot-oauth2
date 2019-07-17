package com.zzizily.oauth2.user.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OauthUserRole", schema = "oauth")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
public class OauthUserRole {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long oauthUserId;
  private String role;

  @Builder
  public OauthUserRole(Long oauthUserId, String role) {
    this.oauthUserId = oauthUserId;
    this.role = role;
  }
}
