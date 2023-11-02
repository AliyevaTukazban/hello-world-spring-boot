package com.example.demo;

import de.htwberlin.webtech.web.Schueler;
import org.springframework.data.repository.CrudRepository;

public interface SchuelerRepository extends CrudRepository<Schueler, Long> {
}
