package com.smart.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.Entities.User;

public interface Repository extends JpaRepository<User, Integer>{

}
