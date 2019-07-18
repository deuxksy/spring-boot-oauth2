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
public class OauthUserRole {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String role;

  @Builder
  public OauthUserRole(String role) {
    this.role = role;
  }
}
