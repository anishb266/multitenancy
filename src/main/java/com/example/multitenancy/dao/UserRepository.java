package com.example.multitenancy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.multitenancy.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
