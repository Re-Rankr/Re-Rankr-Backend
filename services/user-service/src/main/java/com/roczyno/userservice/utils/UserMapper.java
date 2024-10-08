package com.roczyno.userservice.utils;

import com.roczyno.userservice.dto.UserDto;
import com.roczyno.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto mapToUserDto (User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
