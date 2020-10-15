package vix.digital.exercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vix.digital.exercise.entities.HistoryEntity;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Integer> {}