package com.zzizily.oauth2.user.repository;

import com.zzizily.oauth2.user.model.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserVO, String> {

}
