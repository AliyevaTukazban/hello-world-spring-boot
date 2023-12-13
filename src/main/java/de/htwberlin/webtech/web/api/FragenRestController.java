package de.htwberlin.webtech.web.api;

import de.htwberlin.webtech.service.FrageService;
import de.htwberlin.webtech.web.FrageManipulationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public List <Frage> fetchQuestions() {
        return frageService.findAll();

    }
}
