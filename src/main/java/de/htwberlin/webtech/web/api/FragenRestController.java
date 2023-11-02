package de.htwberlin.webtech.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FragenRestController {

    private List<Frage> frage;

    public FragenRestController() {
        frage = new ArrayList<>();
        frage.add(new Frage(1, "1+1"));
        frage.add(new Frage(2, "1-1"));

    }

    @GetMapping(path = "/api/v1/frage")
   public List <Frage> fetchQuestions() {
        return frage;

    }
}
