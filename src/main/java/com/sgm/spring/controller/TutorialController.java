package com.sgm.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sgm.spring.dto.TutorialDTO;
import com.sgm.spring.exception.RecordNotFoundException;
import com.sgm.spring.repository.TutorialRepository;
import com.sgm.spring.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://192.168.1.123:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialService tutorialService;

	@GetMapping("/tutorials")
	public ResponseEntity<List<TutorialDTO>> getAllTutorials() {
//			@RequestParam(required = false) String title) {
		try {
			List<TutorialDTO> tutorials = new ArrayList<TutorialDTO>();

//			if (title == null)
			return new ResponseEntity<>(tutorialService.getAllTutorials()
					,HttpStatusCode.valueOf(200));
//			else
//				tutorialService.findByTitleContaining(title).forEach(tutorials::add);
//			return new ResponseEntity<>(tutorialService.getAllTutorials()
//					,HttpStatusCode.valueOf(200));

//            return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<TutorialDTO> getTutorialById(@PathVariable("id") long id) throws RecordNotFoundException {
		try{
				TutorialDTO tutorialData = tutorialService.getTutorialById(id);
				return new ResponseEntity<>(tutorialService.getTutorialById(id), HttpStatus.OK);
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tutorials")
	public ResponseEntity<TutorialDTO> createTutorial(@RequestBody TutorialDTO tutorial) {
		try {
			TutorialDTO _tutorial = tutorialService.createTutorial(tutorial);
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<TutorialDTO> updateTutorial(@PathVariable("id") long id, @RequestBody TutorialDTO tutorial) throws RecordNotFoundException {
		try {
			TutorialDTO tutorialData = tutorialService.updateTutorial(id, tutorial);
			return new ResponseEntity<>(tutorialData, HttpStatus.OK);
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialService.deleteTutorialById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialService.deleteAllTutorials();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<TutorialDTO>> findByPublished() {
		try {
			List<TutorialDTO> tutorials = tutorialService.findByPublished();

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
