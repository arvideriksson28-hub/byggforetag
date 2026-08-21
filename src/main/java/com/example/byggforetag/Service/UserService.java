package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.UserRequestDto;
import com.example.byggforetag.DTO.UserResponseDto;
import com.example.byggforetag.Enums.Role;
import com.example.byggforetag.Exception.EmailAlreadyExistsException;
import com.example.byggforetag.Exception.UserNotFoundException;
import com.example.byggforetag.Model.Employee;
import com.example.byggforetag.Model.User;
import com.example.byggforetag.Repository.EmployeeRepository;
import com.example.byggforetag.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    public UserService(UserRepository userRepository, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
    }

    public UserResponseDto findByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException(email));
        return UserResponseDto.fromEntity(user);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public List<UserResponseDto> findAllByRole(Role role){
        return userRepository.findAllByRole(role).stream()
                .map(UserResponseDto::fromEntity)
                .toList();
    }

    public UserResponseDto registerUser(UserRequestDto userRequestDto){
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new EmailAlreadyExistsException(userRequestDto.getEmail());
        }

        User user = userRequestDto.toEntity(Role.ROLE_USER);
        return UserResponseDto.fromEntity(userRepository.save(user));
    }

    public UserResponseDto registerEmployee(UserRequestDto userRequestDto){
        if (userRepository.existsByEmail(userRequestDto.getEmail())){
            throw new EmailAlreadyExistsException(userRequestDto.getEmail());
        }

        User user = userRequestDto.toEntity(Role.ROLE_EMPLOYEE);
        userRepository.save(user);

        Employee employee = new Employee(user, new ArrayList<>(), LocalDate.now());
        employeeRepository.save(employee);

        return UserResponseDto.fromEntity(user);
    }

    public UserResponseDto getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
       return UserResponseDto.fromEntity(user);
    }

    public List<UserResponseDto> getallUsers(){
      List<User> users = userRepository.findAll();
      return users.stream()
              .map(UserResponseDto::fromEntity)
              .toList();
    }
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto){
       User user = userRepository.findById(id)
               .orElseThrow(() -> new UserNotFoundException(id));
       if (userRequestDto.getEmail() != null){
           user.setEmail(userRequestDto.getEmail());
       }
       if (userRequestDto.getName() != null){
           user.setName(userRequestDto.getName());
       }
       if (userRequestDto.getPassword() != null){
           user.setPassword(userRequestDto.getPassword());
       }
       return UserResponseDto.fromEntity(userRepository.save(user));
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
        userRepository.delete(user);
    }
}
