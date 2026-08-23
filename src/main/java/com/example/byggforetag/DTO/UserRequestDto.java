package com.example.byggforetag.DTO;

import com.example.byggforetag.Enums.Role;
import com.example.byggforetag.Model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDto {
    @NotBlank(message = "Namn får inte vara tomt")
    private String name;

    @NotBlank(message = "Email får inte vara tomt")
    @Email(message = "Email måste vara giltig")
    private String email;

    @NotBlank(message = "Lösenord får inte vara tomt")
    @Size(min = 6, message = "Lösenordet måste vara minst 6 tecken")
    private String password;

    public UserRequestDto() {}

    public UserRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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
