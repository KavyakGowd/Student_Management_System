package com.sbproject.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbproject.sms.entity.Student;



public interface StudentRepository extends JpaRepository<Student, Long>{

}
