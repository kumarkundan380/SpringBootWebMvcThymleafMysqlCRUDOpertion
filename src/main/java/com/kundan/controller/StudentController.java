package com.kundan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kundan.model.Student;
import com.kundan.service.StudentService;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	// 1. Show Registration Page
	@GetMapping("/register")
	public String showRegistration(Model model) {
		model.addAttribute("student", new Student());
		return "StudentRegistration";
	}
	
	// 2. Save Student
	@PostMapping("/save")
	public String save(@ModelAttribute Student student, Model model) {
		Integer id = studentService.saveStudent(student);
		model.addAttribute("message", "Student '" +id+"' saved");
		// clear form
		model.addAttribute("student", new Student());
		return "StudentRegistration";
	}
	
	// 3.Display all student
//	@GetMapping("/all")
//	public String showAl(Model model) {
//		List<Student> studentList = studentService.getAllStudent();
//		model.addAttribute("list", studentList);
//		return "StudentData";
//	}
	
	@GetMapping("/all")
	public String showPage(@PageableDefault(page = 0, size =3) Pageable pageable, Model model) {
		Page<Student> studentPage = studentService.getPageStudent(pageable);
		model.addAttribute("page", studentPage);
		return "StudentData";
	}
	
	// 4.Delete one Student
	//...../delete?id=__
	@GetMapping("/delete")
	public String delete(@RequestParam Integer id,Model model) {
		String message = null;
		if (studentService.isStudentExist(id)) {
			studentService.deleteStudent(id);
			message = "Student '"+id+"' Deleted";
		} else {
			message = "Student'"+id+"' not exist";
		}
		model.addAttribute("message", message);
		// fetch latest table data
		List<Student> studentList = studentService.getAllStudent();
		model.addAttribute("list", studentList);
		// send to UI
		return "StudentData";
	}
	
	// 5. Show Edit Page
	// .../edit?id=__
	/**
	 * If given input id exist in database
	 * then goto Edit Page, else come to same page
	 * back to Data(all)
	 */
	@GetMapping("/edit")
	public String showEdit(@RequestParam Integer id,Model model) {
		String page = null;
		Optional<Student> student = studentService.getOneStudent(id);
		if(student.isPresent()) {
			// if exist go to edit page
			model.addAttribute("student", student.get());
			page = "StudentEdit";
		} else {
			// given id is not exist in DB
			page = "redirect:all";
		}
		return page;
	}
	
	// 6.Do update
	@PostMapping("/update")
	public String update(@RequestParam Integer id, @ModelAttribute Student student, Model model) {
		if(studentService.isStudentExist(id) ) {
			studentService.updateStudent(student);
			List<Student> studentList = studentService.getAllStudent();
			model.addAttribute("list", studentList);
			model.addAttribute("message","Student '"+id+"' Updated");
			return "StudentData";
		}
		return "redirect:all";
		
	}
}
