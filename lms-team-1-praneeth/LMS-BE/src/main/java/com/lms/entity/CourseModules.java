package com.lms.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coursemodules")
public class CourseModules {

	@Id
	@GeneratedValue(generator = "cmseqgen")
	@SequenceGenerator(name = "cmseqgen", sequenceName = "cmsg", initialValue = 1, allocationSize = 1)
	@JsonProperty(access = Access.WRITE_ONLY)
	private int cmid;

	private int modulenum;

	private String videoinserttime;

//	@ElementCollection(fetch = FetchType.EAGER)
//	@CollectionTable(name = "courselinks", joinColumns = @JoinColumn(name = "fk_coursemoduleid"))
//	private Set<String> clinks;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "courselinktable")
	private List<CourseLink> clinks;

}
