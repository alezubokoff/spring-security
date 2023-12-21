package com.zubokoff.springsecurity.contollers;

import com.zubokoff.springsecurity.dtos.ErrorDTO;
import com.zubokoff.springsecurity.dtos.UserRequestDTO;
import com.zubokoff.springsecurity.dtos.UserResponseDTO;
import com.zubokoff.springsecurity.entities.User;
import com.zubokoff.springsecurity.exceptions.ErrorInsertDataBaseException;
import com.zubokoff.springsecurity.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/login")
    public String login() {
        return "";
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody UserRequestDTO userRequest) {
            User user = userService.create(userRequest);
            UserResponseDTO response = new UserResponseDTO(user.getUserName(), user.getPassword(), user.getRoles());
            return ResponseEntity.ok().body(response);
    }
}
