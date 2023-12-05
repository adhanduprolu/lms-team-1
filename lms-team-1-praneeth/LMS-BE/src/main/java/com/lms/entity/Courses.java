package com.lms.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "courses")
public class Courses {

	@Id
	@GeneratedValue(generator = "cseqgen")
	@SequenceGenerator(name = "cseqgen", sequenceName = "csg", initialValue = 101, allocationSize = 1)
	@JsonProperty(access = Access.WRITE_ONLY)
	private int courseid;

	private String coursename;

	private String coursetrainer;

	private String coursecreatedate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_courseid", referencedColumnName = "courseid", nullable = false)
	private List<CourseModules> coursemodule;

	@ManyToMany(mappedBy = "courseslist", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("courseslist")
	private List<CourseUsers> courseusers;
}
