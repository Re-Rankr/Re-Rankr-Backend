package com.roczyno.userservice.service.UserService;

import com.roczyno.userservice.dto.UserDto;
import com.roczyno.userservice.repository.UserRepository;
import com.roczyno.userservice.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.roczyno.userservice.entity.User;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public List<UserDto> getUser(List<UUID> request) {
        List<User> users = userRepository.findByIdIn(request);
        return users.stream().map(userMapper::mapToUserDto).toList();
    }

//    public User getUser(String email) {
//        userRepository.findByEmail();
//    }
}
