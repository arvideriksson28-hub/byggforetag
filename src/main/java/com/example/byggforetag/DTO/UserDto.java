package com.example.byggforetag.DTO;

import com.example.byggforetag.Enums.Role;
import com.example.byggforetag.Model.User;

public class UserDto {
    private String name;
    private String email;
    private String password;

    public UserDto() {}

    public UserDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static UserDto fromEntity(User user){
        return new UserDto(
                user.getName(),
                user.getEmail(),
                null
        );
    }

    public User toEntity(Role role){
        return new User(this.name, this.email, this.password, role);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
