package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.UserRequestDto;

import com.example.byggforetag.DTO.UserResponseDto;
import com.example.byggforetag.Service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/user")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRequestDto));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> seeProfile(@PathVariable Long id, Principal principal){
        return ResponseEntity.ok(userService.seeOwnProfile(principal.getName(), id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto, Principal principal){
        return ResponseEntity.ok(userService.updateUser(id, userRequestDto, principal.getName()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, Principal principal){
        userService.userDeleteUser(id, principal.getName());
        return ResponseEntity.noContent().build();
    }


}
