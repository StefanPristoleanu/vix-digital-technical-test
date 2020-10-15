package vix.digital.exercise.controllers;

import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import vix.digital.exercise.dtos.ServiceDTO;
import vix.digital.exercise.entities.ServiceEntity;
import vix.digital.exercise.repositories.ServiceRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ServiceController {
	
	final ServiceRepository serviceRepository;

	public ServiceController(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	@CrossOrigin
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ServiceEntity>> getServiceList() {
		return new ResponseEntity<>(serviceRepository.findAll(), HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceEntity> addService(@NotNull @RequestBody ServiceDTO service) {

		ServiceEntity serviceToBeUpdated = serviceRepository.findByName(service.getName());
		if(serviceToBeUpdated != null || StringUtils.isEmpty(service.getUrl())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		ServiceEntity serviceEntity = new ServiceEntity();
		serviceEntity.setName(service.getName());
		serviceEntity.setUrl(service.getUrl());
		serviceRepository.save(serviceEntity);
		return new ResponseEntity<>(serviceEntity, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceEntity> updateService(@NotNull @RequestBody ServiceDTO service) {

		ServiceEntity serviceToBeUpdated = serviceRepository.findByName(service.getName());
		if(serviceToBeUpdated == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		serviceToBeUpdated.setName(service.getName());
		serviceToBeUpdated.setUrl(service.getUrl());
		serviceRepository.save(serviceToBeUpdated);
		return new ResponseEntity<>(serviceToBeUpdated, HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ServiceEntity>> deleteService(@NotNull @RequestBody ServiceDTO service) {

		ServiceEntity serviceToBeDeleted = serviceRepository.findByName(service.getName());
		if(serviceToBeDeleted == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		serviceRepository.delete(serviceToBeDeleted);
		return new ResponseEntity<>(serviceRepository.findAll(), HttpStatus.OK);
	}
}
