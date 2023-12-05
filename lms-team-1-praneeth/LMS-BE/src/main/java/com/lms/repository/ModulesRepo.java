package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.CourseModules;
import java.util.List;

public interface ModulesRepo extends JpaRepository<CourseModules, Integer> {

	List<CourseModules> findByModulenum(int modulenum);
}
