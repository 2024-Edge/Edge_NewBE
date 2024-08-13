package com.hanium.edge.controller;

import com.hanium.edge.dto.LoginRequest;
import com.hanium.edge.dto.RegisterRequest;
import com.hanium.edge.dto.ResponseDTO;
import com.hanium.edge.model.User;
import com.hanium.edge.security.JwtTokenProvider;
import com.hanium.edge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody RegisterRequest registerRequest) {
        User user = userService.registerUser(registerRequest);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK.value(), "User registered with ID: " + user.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication.getName());
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK.value(), "Bearer " + jwt));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteUser(@RequestParam String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK.value(), "User deleted"));
    }
}
