package com.zubokoff.springsecurity.repositories;

import com.zubokoff.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
