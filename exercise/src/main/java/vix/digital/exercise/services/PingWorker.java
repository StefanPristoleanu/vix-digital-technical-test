package vix.digital.exercise.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import vix.digital.exercise.entities.HistoryEntity;
import vix.digital.exercise.entities.ServiceEntity;
import vix.digital.exercise.repositories.HistoryRepository;
import vix.digital.exercise.repositories.ServiceRepository;
import vix.digital.exercise.utils.PollerUtil;

import java.util.Date;
import java.util.List;

public class PingWorker implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingWorker.class);

	private final ServiceRepository serviceRepository;
	private final HistoryRepository historyRepository;

	public PingWorker(ServiceRepository serviceRepository, HistoryRepository historyRepository) {
		this.serviceRepository = serviceRepository;
		this.historyRepository = historyRepository;
	}

	public void run() {
		List<ServiceEntity> serviceEntityList = serviceRepository.findAll();
		for(ServiceEntity se: serviceEntityList) {
			if(StringUtils.isEmpty(se.getUrl())) {
				continue;
			}
			long t = System.currentTimeMillis();
			int pingCode =  PollerUtil.getStatusUnirest(se.getUrl());
			if(pingCode != 200) {
				LOGGER.warn("Code: " + pingCode + " from URL: " + se.getUrl());
			}
			Integer responseTime = (int) (System.currentTimeMillis() - t);
			se.setLatestStatus(pingCode);
			serviceRepository.save(se);

			HistoryEntity rec = new HistoryEntity();
			rec.setServiceId(se.getId());
			rec.setLogDate(new Date());
			rec.setStatus(pingCode);
			rec.setResponseTime(responseTime);
			historyRepository.save(rec);
		}

	}
}