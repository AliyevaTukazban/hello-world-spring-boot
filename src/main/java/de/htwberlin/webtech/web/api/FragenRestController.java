package de.htwberlin.webtech.web.api;

import de.htwberlin.webtech.service.FrageService;
import de.htwberlin.webtech.web.FrageManipulationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FragenRestController {

    private List<Frage> frageList;
    private FrageService frageService;

    public FragenRestController(FrageService frageService) {
        this.frageService = frageService;
        this.frageList = new ArrayList<>();
        this.frageList.add(new Frage(1, "1+1"));
        this.frageList.add(new Frage(2, "1-1"));
    }

    private FrageManipulationRequest convertToFrageManipulationRequest(Frage frage) {
        return new FrageManipulationRequest(frage.getText());
    }

    @PostMapping(path = "/api/v1/frage")
    public ResponseEntity<String> createQuestion(@RequestBody Frage frage) {
        FrageManipulationRequest request = convertToFrageManipulationRequest(frage);

        // Hier rufst du die Methode im FrageService auf
        frageService.create(request);

        // Rückgabe einer Erfolgsmeldung
        return ResponseEntity.status(HttpStatus.CREATED).body("Frage wurde erfolgreich erstellt");
    }

    @GetMapping(path = "/api/v1/frage")
    public List <Frage> fetchQuestions() {
        return frageService.findAll();

    }
    @PutMapping(path = "/api/v1/frage/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Long id, @RequestBody Frage frage) {
        FrageManipulationRequest request = convertToFrageManipulationRequest(frage);

        Frage updatedFrage = frageService.update(id, request);

        if (updatedFrage != null) {
            // Rückgabe einer Erfolgsmeldung
            return ResponseEntity.status(HttpStatus.OK).body("Frage wurde erfolgreich aktualisiert");
        } else {
            // Rückgabe einer Fehlermeldung, wenn die Frage nicht gefunden wurde
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Frage mit ID " + id + " wurde nicht gefunden");
        }
    }

    @DeleteMapping(path = "/api/v1/frage/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        boolean deleted = frageService.deleteById(id);

        if (deleted) {
            // Rückgabe einer Erfolgsmeldung
            return ResponseEntity.status(HttpStatus.OK).body("Frage wurde erfolgreich gelöscht");
        } else {
            // Rückgabe einer Fehlermeldung, wenn die Frage nicht gefunden wurde
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Frage mit ID " + id + " wurde nicht gefunden");
        }
    }
}