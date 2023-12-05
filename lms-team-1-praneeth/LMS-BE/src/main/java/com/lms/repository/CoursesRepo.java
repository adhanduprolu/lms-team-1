package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.entity.Courses;

public interface CoursesRepo extends JpaRepository<Courses, Integer> {

	List<Courses> findBycoursename(String coursename);

	@Query("SELECT c FROM Courses c WHERE c.coursename = :coursename AND c.coursetrainer = :coursetrainer")
	List<Courses> findBycoursenameAndcoursetrainer(String coursename, String coursetrainer);

	boolean existsBycoursename(String coursename);

}
