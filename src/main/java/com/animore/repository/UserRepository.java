package com.animore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.animore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserCode(String userCode);
}
