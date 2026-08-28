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

    //Använder principal för att alltid få inloggades username och slippa checka i servicelagret.
    @GetMapping("/users/me")
    public ResponseEntity<UserResponseDto> getMyProfile(Principal principal){
        return ResponseEntity.ok(userService.findByEmail(principal.getName()));
    }

    //valid så ingen felaktig data kan skickas in
    @PostMapping("/register/user")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRequestDto));
    }

    @PutMapping("/update/user")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto, Principal principal){
        return ResponseEntity.ok(userService.updateUser(userRequestDto, principal.getName()));
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<Void> deleteUser(Principal principal){
        userService.deleteMyAccount(principal.getName());
        return ResponseEntity.noContent().build();
    }


}
