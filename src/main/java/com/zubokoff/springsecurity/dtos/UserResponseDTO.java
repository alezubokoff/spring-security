package com.zubokoff.springsecurity.dtos;

import com.zubokoff.springsecurity.entities.Role;

import java.util.List;

public record UserResponseDTO(String username, String token, List<Role> roles) {
}
