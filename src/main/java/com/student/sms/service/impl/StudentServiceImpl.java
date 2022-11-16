package com.student.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.sms.entity.Student;
import com.student.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	private com.student.sms.repository.StudentRepository StudentRepository;
	
	
	public StudentServiceImpl(com.student.sms.repository.StudentRepository StudentRepository) {
		super();
		this.StudentRepository = StudentRepository;
	}


	@Override
	public List<Student> getAllStudents() {
		return StudentRepository.findAll();
	}


	@Override
	public Student saveStudent(Student student) {
		
		return StudentRepository.save(student);
	}


	@Override
	public Student getStudent(Long id) {
		return StudentRepository.findById(id).get();
	}


	@Override
	public Student updateStudent(Student student) {
		
		return StudentRepository.save(student);
	}


	@Override
	public void deleteStudent(Long id) {

		StudentRepository.deleteById(id);
		
	}


	

}
