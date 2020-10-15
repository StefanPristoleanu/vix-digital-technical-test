package vix.digital.exercise.services;

import org.springframework.stereotype.Service;
import vix.digital.exercise.repositories.HistoryRepository;
import vix.digital.exercise.repositories.ServiceRepository;
import vix.digital.exercise.utils.PollerUtil;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

@Service
public class PingService {

	final
	ServiceRepository serviceRepository;

	final
	HistoryRepository historyRepository;

	public PingService(ServiceRepository serviceRepository, HistoryRepository historyRepository) {
		this.serviceRepository = serviceRepository;
		this.historyRepository = historyRepository;
	}

	@PostConstruct
	public void init() {
		PollerUtil.setup();

		ScheduledThreadPoolExecutor executorService = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);

		PingWorker worker = new PingWorker(serviceRepository, historyRepository);

		executorService.scheduleWithFixedDelay(worker, 2000, 5000, TimeUnit.MILLISECONDS);
	}

}