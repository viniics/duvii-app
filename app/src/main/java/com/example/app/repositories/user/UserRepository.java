package com.example.app.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    boolean existsByEmail(String email);

    User findByEmail(String findBy);

    User findByUserName(String findBy);

    boolean existsByUserName(String userName);
    
}
