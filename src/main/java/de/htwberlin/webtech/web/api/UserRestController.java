package de.htwberlin.webtech.web.api;

import de.htwberlin.webtech.service.UserService;
import de.htwberlin.webtech.web.UserManipulationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    private UserManipulationRequest convertToFrageManipulationRequest(User user) {
        return new UserManipulationRequest(user.getUsername(), user.getEmail(), user.getPassword());
    }

    @PostMapping(path = "/api/v1/user")
    public ResponseEntity<String> createUser(@RequestBody UserManipulationRequest request) {
        User createdUser = userService.createUser(request.getUsername(), request.getEmail(), request.getPassword());
        if (createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User wurde erfolgreich erstellt");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Benutzername oder E-Mail ist bereits vergeben");
        }
    }


    @GetMapping(path = "/api/v1/user")
    public List<User> fetchUser() {
        return userService.findAll();
    }


    @PutMapping(path = "/api/v1/user{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserManipulationRequest request) {
        User updatedUser = userService.update(id, request.getUsername(), request.getEmail(), request.getPassword());
        if (updatedUser != null) {
            return ResponseEntity.ok("User wurde erfolgreich aktualisiert");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User mit der angegebenen ID wurde nicht gefunden");
        }
    }

    @DeleteMapping(path = "/api/v1/user{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (userService.deleteById(id)) {
            return ResponseEntity.ok("User wurde erfolgreich gelöscht");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User mit der angegebenen ID wurde nicht gefunden");
        }

    }
}
