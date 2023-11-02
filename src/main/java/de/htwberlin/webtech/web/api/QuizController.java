package de.htwberlin.webtech.web.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/fragen")
    public Frage createFrage(@RequestBody Frage frage) {
        return quizService.saveFrage(frage);
    }

    @GetMapping("/fragen/{id}")
    public Frage getFrage(@PathVariable String id) {
        Long frageId = Long.parseLong(id);
        return quizService.getFrage(frageId);
    }

    @GetMapping("/fragen")
    public List<Frage> getAllFragen() {
        return quizService.getAllFragen();
    }

    @PostMapping("/antworten")
    public Antwort createAntwort(@RequestBody Antwort antwort) {
        return quizService.saveAntwort(antwort);
    }

    @GetMapping("/antworten/{id}")
    public Antwort getAntwort(@PathVariable String id) {
        Long antwortId = Long.parseLong(id);
        return quizService.getAntwort(antwortId);
    }

    @GetMapping("/antworten")
    public List<Antwort> getAllAntworten() {
        return quizService.getAllAntworten();
    }
}

