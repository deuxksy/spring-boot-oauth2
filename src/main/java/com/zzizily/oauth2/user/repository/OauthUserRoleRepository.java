package com.zzizily.oauth2.user.repository;

import com.zzizily.oauth2.user.model.OauthUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthUserRoleRepository extends JpaRepository<OauthUserRole, Long> {

}
