package com.zubokoff.springsecurity.dtos;

import com.zubokoff.springsecurity.entities.Role;

import java.util.List;

public record UserRequestDTO(String username, String password, List<Role> roles) {
}
