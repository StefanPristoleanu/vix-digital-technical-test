package vix.digital.exercise.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vix.digital.exercise.entities.HistoryEntity;
import vix.digital.exercise.repositories.HistoryRepository;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

	final HistoryRepository historyRepository;

	public HistoryController(HistoryRepository historyRepository) {
		this.historyRepository = historyRepository;
	}

	@CrossOrigin
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HistoryEntity>> getServiceList() {
		return new ResponseEntity<>(historyRepository.findAll(), HttpStatus.OK);
	}
}
