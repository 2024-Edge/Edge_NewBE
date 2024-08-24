package com.hanium.edge.controller;

import com.hanium.edge.dto.ResponseDTO;
import com.hanium.edge.dto.UserDTO;
import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = userService.registerUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName());
        ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), "User registered with ID: " + userEntity.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = userService.loginUser(userDTO.getUsername(), userDTO.getPassword());
        if (userEntity != null) {
            ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), "Login successful");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseDTO response = new ResponseDTO(HttpStatus.UNAUTHORIZED.value(), "Invalid username or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
