package com.zzizily.oauth2.user.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OauthUserRole", schema = "oauth")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class OauthUserRole {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long oauthUserId;
  private String role;
}
