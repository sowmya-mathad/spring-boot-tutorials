package com.sgm.spring.service;

import com.sgm.spring.dto.TutorialDTO;
import com.sgm.spring.exception.ApplicationExceptionHandler;
import com.sgm.spring.exception.RecordNotFoundException;
import com.sgm.spring.model.TutorialEntity;
import com.sgm.spring.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    public TutorialDTO getTutorialById(Long id) throws RecordNotFoundException {
        TutorialDTO TutorialDTO = null;
        Optional<TutorialEntity> optionalEntity = tutorialRepository.findById(id);
        if (optionalEntity.isPresent()) {
            return mapEntityToDTO(optionalEntity.get());
        } else {
            throw new RecordNotFoundException("Employee is not associated to amy department");
        }
    }

    public TutorialDTO createTutorial(TutorialDTO tutorial) {
        try {

            TutorialEntity _tutorial = tutorialRepository.save(mapDTOToEntity(tutorial));
            return mapEntityToDTO(_tutorial);

        } catch (Exception e) {
            throw e;
        }
    }

    public TutorialDTO updateTutorial(long id, TutorialDTO tutorial) throws RecordNotFoundException {
        Optional<TutorialEntity> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            TutorialEntity _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            _tutorial = tutorialRepository.save(_tutorial);
            return mapEntityToDTO(_tutorial);
        } else {
            throw new RecordNotFoundException("No Record Found");
        }
    }

    public void deleteTutorialById(long id) throws RecordNotFoundException {
        try {
            tutorialRepository.deleteById(id);
        } catch (Exception e) {
            throw new RecordNotFoundException("No Record Found");
        }
    }

    public void deleteAllTutorials() throws RecordNotFoundException {
        try {
            tutorialRepository.deleteAll();
        } catch (Exception e) {
            throw new RecordNotFoundException("No Records in table");
        }
    }

    public List<TutorialDTO> getAllTutorials() {
        List<TutorialEntity> tutorialEntityList = tutorialRepository.findAll();
        List<TutorialDTO> tutorialDTOList = tutorialEntityList.stream().
                map(this::mapEntityToDTO).toList();

        return tutorialDTOList;
    }

    public List<TutorialDTO> findByPublished() {
        List<TutorialDTO> tutorialDTOs = new ArrayList<>();
        List<TutorialEntity> tutorials = tutorialRepository.findByPublished(true);

        if (tutorials.isEmpty()) {
            return tutorialDTOs;
        } else {
            tutorialDTOs = tutorials.stream().map((en) -> mapEntityToDTO(en)).toList();
            return tutorialDTOs;
        }
    }

    private TutorialDTO mapEntityToDTO(TutorialEntity TutorialEntity) {
        return new TutorialDTO(TutorialEntity.getId(), TutorialEntity.getTitle(),
                TutorialEntity.getDescription(), TutorialEntity.isPublished());
    }

    private TutorialEntity mapDTOToEntity(TutorialDTO TutorialDTO) {
        return new TutorialEntity(TutorialDTO.getId(), TutorialDTO.getTitle(),
                TutorialDTO.getDescription(), TutorialDTO.isPublished());
    }

    public List<TutorialDTO> findByTitleContaining(String title) {
        List<TutorialEntity> tutorialEntityList = tutorialRepository.findByTitleContaining(title);
        List<TutorialDTO> tutorialDTOList = tutorialEntityList.stream().
                map(this::mapEntityToDTO).toList();
        return tutorialDTOList;
    }
}