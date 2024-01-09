package service;
import de.htwberlin.webtech.persistence.FrageEntity;
import de.htwberlin.webtech.persistence.FrageRepository;
import de.htwberlin.webtech.service.FrageService;
import de.htwberlin.webtech.web.FrageManipulationRequest;
import de.htwberlin.webtech.web.api.Frage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FrageServiceTest {

    @Mock
    private FrageRepository frageRepository;

    @InjectMocks
    private FrageService frageService;

    @Test
    void testFindAll() {
        // Mocking
        List<FrageEntity> mockFragen = Arrays.asList(
                new FrageEntity("Frage 1"),
                new FrageEntity("Frage 2")
        );
        when(frageRepository.findAll()).thenReturn(mockFragen);

        // Test
        List<Frage> result = frageService.findAll();

        // Verify
        assertEquals(mockFragen.size(), result.size());
        assertEquals(mockFragen.get(0).getText(), result.get(0).getText());
        assertEquals(mockFragen.get(1).getText(), result.get(1).getText());
    }

    @Test
    void update_ReturnsUpdatedFrage_WhenIdExists() {
        Long id = 1L;
        FrageEntity existingFrageEntity = new FrageEntity("Bestehende Frage");
        existingFrageEntity.setId(id);

        when(frageRepository.findById(id)).thenReturn(Optional.of(existingFrageEntity));

        FrageManipulationRequest request = new FrageManipulationRequest();
        request.setText("Aktualisierte Frage");

        when(frageRepository.save(any(FrageEntity.class))).thenAnswer(invocation -> {
            FrageEntity savedEntity = invocation.getArgument(0);
            return savedEntity;
        });

        Frage result = frageService.update(id, request);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Aktualisierte Frage", result.getText());

        verify(frageRepository, times(1)).findById(id);
        verify(frageRepository, times(1)).save(any(FrageEntity.class));
    }

    @Test
    void update_ReturnsNull_WhenIdDoesNotExist() {
        Long id = 1L;

        when(frageRepository.findById(id)).thenReturn(Optional.empty());

        FrageManipulationRequest request = new FrageManipulationRequest();
        request.setText("Aktualisierte Frage");

        Frage result = frageService.update(id, request);

        assertNull(result);

        verify(frageRepository, times(1)).findById(id);
        verify(frageRepository, never()).save(any(FrageEntity.class));
    }

    @Test
    void deleteById_ReturnsTrue_WhenIdExists() {
        Long id = 1L;
        FrageEntity existingFrageEntity = new FrageEntity("Bestehende Frage");
        existingFrageEntity.setId(id);

        when(frageRepository.existsById(id)).thenReturn(true);

        assertTrue(frageService.deleteById(id));

        verify(frageRepository, times(1)).existsById(id);
        verify(frageRepository, times(1)).deleteById(id);
    }
    @Test
    void deleteById_ReturnsFalse_WhenIdDoesNotExist() {
        Long id = 1L;

        when(frageRepository.existsById(id)).thenReturn(false);

        assertFalse(frageService.deleteById(id));

        verify(frageRepository, times(1)).existsById(id);
        verify(frageRepository, never()).deleteById(id);
    }

    @Test
    void testCreate() {
        // Mocking
        FrageManipulationRequest request = new FrageManipulationRequest("Neue Frage");
        FrageEntity savedEntity = new FrageEntity(request.getText());
        when(frageRepository.save(any())).thenReturn(savedEntity);

        // Test
        Frage result = frageService.create(request);

        // Verify
        assertEquals(request.getText(), result.getText());
    }

    @Test
    void testUpdate() {
        // Mocking
        long questionId = 1L;
        FrageManipulationRequest request = new FrageManipulationRequest("Geänderte Frage");
        FrageEntity existingEntity = new FrageEntity("Vorhandene Frage");
        when(frageRepository.findById(questionId)).thenReturn(Optional.of(existingEntity));
        when(frageRepository.save(any())).thenReturn(existingEntity);

        // Test
        Frage result = frageService.update(questionId, request);

        // Verify
        assertEquals(request.getText(), result.getText());
    }

    @Test
    void testDeleteById() {
        // Mocking
        long questionId = 1L;
        when(frageRepository.existsById(questionId)).thenReturn(true);

        // Test
        boolean result = frageService.deleteById(questionId);

        // Verify
        assertTrue(result);
        verify(frageRepository, times(1)).deleteById(questionId);
    }

    @Test
    void testDeleteByIdNotFound() {
        // Mocking
        long questionId = 1L;
        when(frageRepository.existsById(questionId)).thenReturn(false);

        // Test
        boolean result = frageService.deleteById(questionId);

        // Verify
        assertFalse(result);
        verify(frageRepository, never()).deleteById(questionId);
    }
    @Test
    void testFindAllEmptyList() {
        // Mocking
        when(frageRepository.findAll()).thenReturn(Collections.emptyList());

        // Test
        List<Frage> result = frageService.findAll();

        // Verify
        assertTrue(result.isEmpty());
    }


    @Test
    void testUpdateNonExistentQuestion() {
        // Mocking
        long questionId = 1L;
        FrageManipulationRequest request = new FrageManipulationRequest("Geänderte Frage");

        // Test
        Frage result = frageService.update(questionId, request);

        // Verify
        assertNull(result);
        verify(frageRepository, never()).save(any());
    }
    @Test
    void testFindAllWithMultipleEntities() {
        // Mocking
        List<FrageEntity> mockFragen = Arrays.asList(
                new FrageEntity("Frage 1"),
                new FrageEntity("Frage 2"),
                new FrageEntity("Frage 3")
        );
        when(frageRepository.findAll()).thenReturn(mockFragen);

        // Test
        List<Frage> result = frageService.findAll();

        // Verify
        assertEquals(mockFragen.size(), result.size());
        assertEquals(mockFragen.get(0).getText(), result.get(0).getText());
        assertEquals(mockFragen.get(1).getText(), result.get(1).getText());
        assertEquals(mockFragen.get(2).getText(), result.get(2).getText());
    }

    @Test
    void testUpdateWithEmptyText() {
        // Mocking
        long questionId = 1L;
        FrageManipulationRequest request = new FrageManipulationRequest("");

        // Test
        Frage result = frageService.update(questionId, request);

        // Verify
        assertNull(result);
        verify(frageRepository, never()).save(any());
    }

    @Test
    void testDeleteByIdSuccessfully() {
        // Mocking
        long questionId = 1L;
        when(frageRepository.existsById(questionId)).thenReturn(true);

        // Test
        boolean result = frageService.deleteById(questionId);

        // Verify
        assertTrue(result);
        verify(frageRepository, times(1)).deleteById(questionId);
    }

    @Test
    void testDeleteByIdNotExisting() {
        // Mocking
        long questionId = 1L;
        when(frageRepository.existsById(questionId)).thenReturn(false);

        // Test
        boolean result = frageService.deleteById(questionId);

        // Verify
        assertFalse(result);
        verify(frageRepository, never()).deleteById(questionId);
    }

}

