package com.student.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.student.sms.entity.Student;
import com.student.sms.service.StudentService;

@Controller
public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	//handler method
	@GetMapping("/students")
	public String listStudent(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		//Create student object to hold student form data
		Student student= new Student();
		model.addAttribute("student", student);
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
		
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudent(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudent(id));
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {
		
		//get student from DB by Id
		Student existingStudent = studentService.getStudent(id);
		existingStudent.setId(id);
		existingStudent.setFirst_name(student.getFirst_name());
		existingStudent.setLast_name(student.getLast_name());
		existingStudent.setEmail(student.getEmail());
		
		//Save update Information
		studentService.updateStudent(existingStudent);
		return "redirect:/students";
	}
	
	
	@GetMapping("/students/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students";
		
	}
}
