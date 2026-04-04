package com.test.student.repository;

import com.test.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends  JpaRepository<Student,Integer> {
    public List<Student> findAllByName(String name);
    boolean existsByEmail(String email);
    //List<Student> findAll();

}
