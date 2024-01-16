package com.sujit.StudentManagementAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sujit.StudentManagementAPI.model.Student;
import com.sujit.StudentManagementAPI.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

//	for add new student information
	@PostMapping
	public Student addStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}

//	get student list
	@GetMapping
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

//	get student by id
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id) {
		return studentRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with ID: " + id));
	}

//	update student info by id
	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
		Student existingStudent = studentRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with ID: " + id));
		if (existingStudent != null) {
			existingStudent.setName(updatedStudent.getName());
			existingStudent.setAge(updatedStudent.getAge());
			existingStudent.setEmail(updatedStudent.getEmail());
			existingStudent.setPhone(updatedStudent.getPhone());
			existingStudent.setLocation(updatedStudent.getLocation());
			return studentRepository.save(existingStudent);
		}
		return null;
	}

//	delete student by id
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Long id) {
		if (!studentRepository.existsById(id)) {
			return "User with id " + id + " not found";
		}
		studentRepository.deleteById(id);
		return "User with id " + id + " has Deleted";
	}

}
