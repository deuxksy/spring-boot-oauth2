package com.zzizily.oauth2.user.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OauthUser", schema = "oauth")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Where(clause = "use_yn = 'Y'") //조회 조건
public class OauthUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  private int siteId;
  private String useYn;

  @OneToMany(targetEntity = OauthUserRole.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "oauthUserId")
  private List<OauthUserRole> roles;

  @Override
  public String toString() {
    return "OauthUser{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", password='" + password + '\'' +
      ", siteId=" + siteId +
      ", roles=" + roles +
      '}';
  }
}
