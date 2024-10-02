package com.smart.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	 User findByEmail(String email);

}
