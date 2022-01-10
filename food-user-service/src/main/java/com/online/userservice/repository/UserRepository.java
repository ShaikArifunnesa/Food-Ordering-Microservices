package com.online.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.userservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

			
	Optional<User> findByUserName(String userName);

	Optional<User> findByEmailId(String emailId);

	Optional<User> findByPhoneNumber(String phoneNumber);

	//void deleteByUserId(Integer userId);

	User findByUserIdEquals(Integer userId);

	Optional<User> findByEwallet(double ewallet);

	

	

	



}
