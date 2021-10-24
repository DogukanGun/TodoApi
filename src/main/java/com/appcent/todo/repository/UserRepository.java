package com.appcent.todo.repository;

import com.appcent.todo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> getUserByUsername(String username);
}
