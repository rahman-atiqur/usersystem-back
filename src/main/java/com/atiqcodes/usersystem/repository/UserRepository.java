package com.atiqcodes.usersystem.repository;

import com.atiqcodes.usersystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}


