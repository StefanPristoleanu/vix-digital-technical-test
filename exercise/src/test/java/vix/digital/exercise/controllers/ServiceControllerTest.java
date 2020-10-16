package vix.digital.exercise.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.hamcrest.CoreMatchers.is;
import vix.digital.exercise.dtos.ServiceDTO;
import vix.digital.exercise.entities.ServiceEntity;
import vix.digital.exercise.repositories.ServiceRepository;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ServiceControllerTest {

	private final static String TEST_SERVICE_NAME = "test-service";
	private final static String TEST_SERVICE_URL = "https://test-url.com";

	@Autowired
	ServiceRepository serviceRepository;

	@BeforeEach
	void setUp() {
		//set up a test-specific database
	}


	void getServiceList() {
		ServiceController serviceController = new ServiceController(serviceRepository);
		ResponseEntity<List<ServiceEntity>> listResponseEntity = serviceController.getServiceList();
		assertEquals(listResponseEntity.getStatusCode(), HttpStatus.OK);
		//assuming the db has some data from the set-up phase
		assertThat(Objects.requireNonNull(listResponseEntity.getBody()).isEmpty(), is(false));
	}

	void addService() {
		ServiceDTO serviceDTO = new ServiceDTO();
		serviceDTO.setName(TEST_SERVICE_NAME);
		serviceDTO.setUrl(TEST_SERVICE_URL);

		//add a new ServiceEntity
		//check against the 204 status code
	}

	@Test
	void updateService() {
		//add a ServiceEntity
		//update the same ServiceEntity
		//assert against code 200
		//assert against the new fields
	}

	@Test
	void deleteService() {
		//add a new ServiceEntity
		//delete the aforementioned ServiceEntity
		//assert against code 200
		//assert against the response List<ServiceEntity> not to contain the initial ServiceEntity
	}
}