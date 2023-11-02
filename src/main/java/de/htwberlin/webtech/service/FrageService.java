package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.FrageEntity;
import de.htwberlin.webtech.persistence.FrageRepository;
import de.htwberlin.webtech.web.FrageManipulationRequest;
import de.htwberlin.webtech.web.api.Frage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FrageService {

    private final FrageRepository frageRepository;

    public FrageService(FrageRepository frageRepository) {
        this.frageRepository = frageRepository;
    }

    public List<Frage> findAll() {
        List<FrageEntity> fragen = frageRepository.findAll();
        return fragen.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Frage findById(Long id) {
        var frageEntity = frageRepository.findById(id);
        return frageEntity.map(this::transformEntity).orElse(null);
    }

    public Frage create(FrageManipulationRequest request) {
        var frageEntity = new FrageEntity(request.getText());
        frageEntity = frageRepository.save(frageEntity);
        return transformEntity(frageEntity);
    }
    public Frage update(Long id, FrageManipulationRequest request) {
        var frageEntityOptional = frageRepository.findById(id);
        if (frageEntityOptional.isEmpty()) {
            return null;
        }

        var frageEntity = frageEntityOptional.get();
        frageEntity.setText(request.getText());
        frageEntity = frageRepository.save(frageEntity);

        return transformEntity(frageEntity);
    }

    public boolean deleteById(Long id) {
        if (!frageRepository.existsById(id)) {
            return false;
        }

        frageRepository.deleteById(id);
        return true;
    }

    private Frage transformEntity(FrageEntity frageEntity) {
        return new Frage(
                frageEntity.getId(),
                frageEntity.getText()
        );
    }

}