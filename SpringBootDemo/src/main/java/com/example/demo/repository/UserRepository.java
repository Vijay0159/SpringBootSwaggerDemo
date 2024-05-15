package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	@Modifying
    @Transactional
    @Query("DELETE FROM UserEntity")
    void deleteAllRecords();
}
