package com.zubokoff.springsecurity.contollers;

import com.zubokoff.springsecurity.dtos.UserRequestLoginDTO;
import com.zubokoff.springsecurity.dtos.UserRequestDTO;
import com.zubokoff.springsecurity.dtos.UserResponseDTO;
import com.zubokoff.springsecurity.entities.User;
import com.zubokoff.springsecurity.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/public/users")
    public ResponseEntity<?> create(@RequestBody UserRequestDTO userRequest) {
        User user = userService.create(userRequest);
        UserResponseDTO response = new UserResponseDTO(user.getUserName(), user.getToken(), user.getRoles());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/users")
    public String login(@RequestBody UserRequestLoginDTO userLoginDTO) {
        return "usuário autenticado";
    }
}
