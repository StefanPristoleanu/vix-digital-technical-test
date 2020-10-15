package vix.digital.exercise.entities;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Entity
@Table(name="services")
@Data
public class ServiceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "url")
	private String url;

	@Column(name = "latest_status")
	private Integer latestStatus;
}
