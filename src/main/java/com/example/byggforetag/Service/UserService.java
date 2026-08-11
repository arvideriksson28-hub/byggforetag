package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.UserDto;
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

    public Optional<User> findByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public List<User> findAllByRole(Role role){
        return userRepository.findAllByRole(role);
    }

    public UserDto registerUser(UserDto userDto){
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException(userDto.getEmail());
        }

        User user = userDto.toEntity(Role.ROLE_USER);
        return UserDto.fromEntity(userRepository.save(user));
    }

    public UserDto registerEmployee(UserDto userDto){
        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new EmailAlreadyExistsException(userDto.getEmail());
        }

        User user = userDto.toEntity(Role.ROLE_EMPLOYEE);
        userRepository.save(user);

        Employee employee = new Employee(user, new ArrayList<>(), LocalDate.now());
        employeeRepository.save(employee);

        return UserDto.fromEntity(user);
    }

    public UserDto getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
       return UserDto.fromEntity(user);
    }

    public List<UserDto> getallUsers(){
      List<User> users = userRepository.findAll();
      return users.stream()
              .map(UserDto::fromEntity)
              .toList();
    }
    public UserDto updateUser(Long id,UserDto userDto){
       User user = userRepository.findById(id)
               .orElseThrow(() -> new UserNotFoundException(id));
       if (userDto.getEmail() != null){
           user.setEmail(userDto.getEmail());
       }
       if (userDto.getName() != null){
           user.setName(userDto.getName());
       }
       if (userDto.getPassword() != null){
           user.setPassword(userDto.getPassword());
       }
       return UserDto.fromEntity(userRepository.save(user));
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
        userRepository.delete(user);
    }
}
