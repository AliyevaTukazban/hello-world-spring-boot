package de.htwberlin.webtech.web.api;

import de.htwberlin.webtech.persistence.FrageEntity;
import de.htwberlin.webtech.service.FrageService;
import de.htwberlin.webtech.web.FrageManipulationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FragenRestController {

    private FrageService frageService;

    public FragenRestController(FrageService frageService) {
        this.frageService = frageService;
    }

    private FrageManipulationRequest convertToFrageManipulationRequest(Frage frage) {
        return new FrageManipulationRequest(frage.getText());
    }

    @PostMapping(path = "/api/v1/frage")
    public ResponseEntity<String> createQuestion(@RequestBody Frage frage) {
        FrageManipulationRequest request = convertToFrageManipulationRequest(frage);
        frageService.create(request);

        // Rückgabe einer Erfolgsmeldung
        return ResponseEntity.status(HttpStatus.CREATED).body("Frage wurde erfolgreich erstellt");
    }

    @GetMapping(path = "/api/v1/frage")
    public List<Frage> fetchQuestions() {
        return frageService.findAll();
    }
    @PutMapping(path = "/api/v1/frage/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Long id, @RequestBody FrageManipulationRequest request) {
        // Überprüfen, ob die Frage mit der angegebenen ID existiert
        Optional<FrageEntity> optionalFrageEntity = frageService.findById(id);
        if (optionalFrageEntity.isPresent()) {
            // Aktualisieren der Frage
            frageService.update(id, request);
            return ResponseEntity.ok("Frage wurde erfolgreich aktualisiert");
        } else {
            // Falls die Frage nicht gefunden wurde, Rückgabe einer Fehlermeldung
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Frage mit der angegebenen ID wurde nicht gefunden");
        }
    }
    @DeleteMapping(path = "/api/v1/frage/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        // Überprüfen, ob die Frage mit der angegebenen ID existiert
        Optional<FrageEntity> optionalFrageEntity = frageService.findById(id);
        if (optionalFrageEntity.isPresent()) {
            // Löschen der Frage
            frageService.deleteById(id);
            return ResponseEntity.ok("Frage wurde erfolgreich gelöscht");
        } else {
            // Falls die Frage nicht gefunden wurde, Rückgabe einer Fehlermeldung
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Frage mit der angegebenen ID wurde nicht gefunden");
        }
    }

}

