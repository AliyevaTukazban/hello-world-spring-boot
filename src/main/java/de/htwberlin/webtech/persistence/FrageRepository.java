package de.htwberlin.webtech.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrageRepository extends JpaRepository<FrageEntity, Long> {

    List<FrageEntity> findAllByText(String text);

}
