package com.onurkus.graduationproject.controller;

import com.onurkus.graduationproject.dto.UserDto;
import com.onurkus.graduationproject.entity.User;
import com.onurkus.graduationproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity userSave(@RequestBody User user)
    {
        UserDto userDto=userService.save(user);
        return ResponseEntity.ok("Kullanıcı kaydedildi.(" +userDto.getFullName()+")");
    }



}
