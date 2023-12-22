package com.zubokoff.springsecurity.repositories;

import com.zubokoff.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.userName = ?1 AND u.password =?2")
    User findByUsernameAndPassword(String username, String password);
}
