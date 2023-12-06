package com.lms.dto;

import java.util.LinkedHashSet;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {

	@NotEmpty(message = "Course name cannot be empty")
	private String cname;

	@NotEmpty(message = "name cannot be empty")
	private String name;
	
	@NotEmpty(message = "videoname cannot be empty")
	private List<String> videoname;

	@Positive(message = "modulenum cannot be negative or empty")
	private int modulenum;
	
	@NotEmpty(message = "videolink cannot be empty")
	private LinkedHashSet<String> videolink;

	@NotEmpty(message = "tname cannot be empty")
	private String tname;

	
	
	
}
