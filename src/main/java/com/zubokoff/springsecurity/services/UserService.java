package com.zubokoff.springsecurity.services;

import com.zubokoff.springsecurity.dtos.UserRequestDTO;
import com.zubokoff.springsecurity.entities.User;
import com.zubokoff.springsecurity.exceptions.ErrorInsertDataBaseException;
import com.zubokoff.springsecurity.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String login() {
        return "";
    }

    public User create(UserRequestDTO userRequest) {
        try {
//            var password = new BCryptPasswordEncoder().encode(user.password());
//            System.out.println(new BCryptPasswordEncoder().matches(user.password(), password));
            var password = new BCryptPasswordEncoder().encode(userRequest.password());
            User user = new User(null, userRequest.username(), password, userRequest.roles());
            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new ErrorInsertDataBaseException();
        }
    }
}
