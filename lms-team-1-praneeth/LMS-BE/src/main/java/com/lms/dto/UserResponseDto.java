package com.lms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

	private long id;
	
	private String name;
	
	private String email;

	private String token;

	private String roles;

	private String image;

	@JsonIgnoreProperties({"useremail","username"})
	private UserCoursesDto userCourses;
	
	
}
