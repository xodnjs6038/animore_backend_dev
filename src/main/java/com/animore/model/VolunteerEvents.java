package com.animore.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ani_volunteer_events")
public class VolunteerEvents {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long placeId;
	private Long round;
	private Long status;
	private LocalDate date;
	private String time;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String description;
	private Long number;
	private Long createdBy;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
