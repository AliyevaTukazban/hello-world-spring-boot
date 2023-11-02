package de.htwberlin.webtech.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private FrageRepository frageRepository;

    @Autowired
    private AntwortRepository antwortRepository;

    public Frage saveFrage(Frage frage) {
        return frageRepository.save(frage);
    }

    public Frage getFrage(Long id) {
        return frageRepository.findById(id).orElse(null);
    }

    public List<Frage> getAllFragen() {
        return frageRepository.findAll();
    }

    public Antwort saveAntwort(Antwort antwort) {
        return antwortRepository.save(antwort);
    }

    public Antwort getAntwort(Long id) {
        return antwortRepository.findById(id).orElse(null);
    }

    public List<Antwort> getAllAntworten() {
        return antwortRepository.findAll();
    }
}
