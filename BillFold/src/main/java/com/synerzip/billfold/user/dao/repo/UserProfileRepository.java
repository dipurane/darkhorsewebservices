package com.synerzip.billfold.user.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synerzip.billfold.user.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	
}
