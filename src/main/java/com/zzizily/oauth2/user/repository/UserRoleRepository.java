package com.zzizily.oauth2.user.repository;

import com.zzizily.oauth2.user.model.UserRoleVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleVO, String> {

}
