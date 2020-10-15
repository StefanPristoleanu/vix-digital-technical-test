package vix.digital.exercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vix.digital.exercise.entities.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {

	@Query("SELECT s FROM ServiceEntity s WHERE s.name = ?1")
	ServiceEntity findByName(String name);
}
