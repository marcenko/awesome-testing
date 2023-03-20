package de.cronos.awesometesting.controller;

import de.cronos.awesometesting.db.UserRepository;
import de.cronos.awesometesting.db.model.User;
import de.cronos.awesometesting.db.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable long id ) {
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()) {
            User user = userOpt.get();
            return ResponseEntity.ok(UserDTO.builder()
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .build()
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDTO user) {
        userRepository.save(User.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .build()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
