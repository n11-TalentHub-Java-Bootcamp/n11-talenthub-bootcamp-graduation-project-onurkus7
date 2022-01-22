package com.onurkus.graduationproject.service;

import com.onurkus.graduationproject.converter.UserMapper;
import com.onurkus.graduationproject.dto.UserDto;
import com.onurkus.graduationproject.entity.User;
import com.onurkus.graduationproject.service.entityservice.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserEntityService userEntityService;

    public UserDto save(User user)
    {
        user=userEntityService.save(user);
        UserDto userDto= UserMapper.INSTANCE.convertToUserDto(user);
        return userDto;
    }

}
