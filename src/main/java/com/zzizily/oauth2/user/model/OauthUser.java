package com.zzizily.oauth2.user.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OauthUser", schema = "oauth")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(of = {"id"})
//@Where(clause = "use_yn = 'Y'") //조회 조건
public class OauthUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  private int siteId;
  private String useYn;

  @OneToMany(targetEntity = OauthUserRole.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "id")
  private List<OauthUserRole> roles;

  @Builder
  public OauthUser(String username, String password, int siteId, String useYn, List<OauthUserRole> roles) {
    this.username = username;
    this.password = password;
    this.siteId = siteId;
    this.useYn = useYn;
    this.roles = roles;
  }
}
