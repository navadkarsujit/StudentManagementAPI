package com.sujit.StudentManagementAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sujit.StudentManagementAPI.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
