package animore.animore.controller;

import animore.animore.dto.UserDto;
import animore.animore.mapper.UserMapper;
import animore.animore.model.User;
import animore.animore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok().body(userMapper.toDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        User createdUser = userService.createUser(userDto);
        return userMapper.toDto(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return ResponseEntity.ok(userMapper.toDto(userService.updateUser(id, userDetails)));
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
