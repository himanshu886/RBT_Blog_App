package com.rbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
