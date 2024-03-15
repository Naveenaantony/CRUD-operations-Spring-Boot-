package org.jsp.crudpracticeapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.crudpracticeapp.dto.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students, Integer>{
	
	   List<Students> findByName(String name);
	
       Optional<Students> findByNameAndEmail(String name, String email);
       
       

}
