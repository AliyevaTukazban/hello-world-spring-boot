package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.UserEntity;
import de.htwberlin.webtech.persistence.UserRepository;
import de.htwberlin.webtech.web.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> findUserByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }
    public User createUser(String username, String email, String password) {
        // Überprüfen, ob der Benutzername oder die E-Mail bereits vorhanden sind
        if (userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent()) {
            // Benutzername oder E-Mail ist bereits vergeben
            // geeignete Fehlerbehandlung hinzufügen
            return null;
        }


        // Benutzer erstellen und in die Datenbank speichern
        UserEntity newUserEntity = new UserEntity(null, username, email, password);
        newUserEntity = userRepository.save(newUserEntity);
        // Hier könnten Sie transformEntity(newUserEntity) verwenden, um ein User-Objekt zurückzugeben, wenn benötigt

        return transformEntity(newUserEntity);
    }

    public void deleteUserByUsername(String username) {
        // Benutzer anhand des Benutzernamens aus der Datenbank löschen
        userRepository.findByUsername(username).ifPresent(userRepository::delete);
    }

    public User update(Long id, String username, String email, String password) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userEntity.setUsername(username);
            userEntity.setEmail(email);
            userEntity.setPassword(password);
            userEntity = userRepository.save(userEntity);

            return transformEntity(userEntity);
        } else {
            // Benutzer mit der angegebenen ID wurde nicht gefunden
            return null;
        }
    }

    public boolean deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            // Benutzer mit der angegebenen ID wurde nicht gefunden
            return false;
        }
    }


    public User transformEntity(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }

}
