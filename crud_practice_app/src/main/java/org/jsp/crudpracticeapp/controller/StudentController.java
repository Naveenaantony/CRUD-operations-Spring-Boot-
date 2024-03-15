package org.jsp.crudpracticeapp.controller;

import java.util.List;
import java.util.Optional;

import org.jsp.crudpracticeapp.dto.Students;
import org.jsp.crudpracticeapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

@RestController
@RequestMapping(value = "/students")
public class StudentController {
	@Autowired
	private StudentRepository sr;
	
	@PostMapping
	public Students saveStudent(@RequestBody Students st) 
	{
		return sr.save(st);
	}
	
	@GetMapping
	public List<Students> finAll()
	{
		return sr.findAll();
	}
	
	@GetMapping(value = "/find-by-name/{name}")
	public List<Students> findbyname(@PathVariable(name = "name") String name)
	{
		return sr.findByName(name);
	}
	
	
	@PostMapping(value = "/verify-by-email")
	public Students verify(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email) {
		Optional<Students> resStudent = sr.findByNameAndEmail(name, email);
		if (resStudent.isPresent()) {
			return resStudent.get();
		}
		return null;
	}
	
	@PutMapping
	public Students updateStudent(@RequestBody Students st)
	{
		Optional<Students> resstudent= sr.findById(st.getId());
		if(resstudent.isPresent())
		{
			Students dbStudents=resstudent.get();
			dbStudents.setName(st.getName());
			dbStudents.setAge(st.getAge());
			dbStudents.setEmail(st.getEmail());
			return sr.save(dbStudents);
		}
		else
			return null;
	}
	
	
	@DeleteMapping(value = "/{id}")
	public String deleteStudent(@PathVariable(name = "id") int id)
	{
		Optional<Students> resStudent=sr.findById(id);
		if(resStudent.isPresent())
		{
			return "user deleted";
		}
		else
		{
			return "invalid id";
		}
	}

}
