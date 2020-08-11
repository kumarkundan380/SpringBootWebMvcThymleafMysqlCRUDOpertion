package com.kundan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kundan.model.Student;


public interface StudentService {
	
	public Integer saveStudent(Student student);
	
	public void updateStudent(Student student);
	
	public void deleteStudent(Integer stdId);
	
	public Optional<Student> getOneStudent(Integer stdId);
	
	public List<Student> getAllStudent();
	
	public Page<Student> getPageStudent(Pageable pageable);
	
	public boolean isStudentExist(Integer stdId);

}
