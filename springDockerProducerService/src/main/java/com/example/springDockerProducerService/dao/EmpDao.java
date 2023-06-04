package com.example.springDockerProducerService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springDockerProducerService.models.Emp;

@Repository
public interface EmpDao extends JpaRepository<Emp, Integer> {

	@Modifying
	@Query("Update Emp e set e.name= ?1, e.age= ?2 where e.id= ?3")
	public void updateEmp(String name, Integer age, Integer id);
}