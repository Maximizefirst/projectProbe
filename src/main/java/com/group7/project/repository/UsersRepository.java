package com.group7.project.repository;

import com.group7.project.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {
    Optional<UsersModel> findByUsernameAndPassword(String username, String password);
    UsersModel findByUsername(String username);
}
