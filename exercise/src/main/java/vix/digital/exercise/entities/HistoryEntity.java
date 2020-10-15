package vix.digital.exercise.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="history")
@Data
public class HistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "service_id")
	private Integer serviceId;

	@Column(name = "status")
	private Integer status;

	@Column(name = "response_time")
	private Integer responseTime;

	@Column(name = "log_date")
	private Date logDate;
}
