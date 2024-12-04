package edu.du.sb1202_test_lms.service;

import edu.du.sb1202_test_lms.dto.UserDto;
import edu.du.sb1202_test_lms.entity.Role;
import edu.du.sb1202_test_lms.entity.User;
import edu.du.sb1202_test_lms.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.STUDENT);
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getAllInstructors() {
        return userRepository.findByRole(Role.INSTRUCTOR); // Role.INSTRUCTOR는 강사 역할을 나타냄
    }
}

