package com.test.student.controller;

import com.test.student.dto.StudentRequestDTO;
import com.test.student.dto.StudentResponseDTO;
import com.test.student.entity.Student;
import com.test.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/api/v1/students/add")
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO requestDTO)
    {
        StudentResponseDTO responseDTO=service.createNewStudent(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/api/v1/students/bulk")
    public ResponseEntity<List<StudentResponseDTO>> createStudents(@Valid @RequestBody List<Student> students)
    {
        List<StudentResponseDTO> savedStudents=service.createStudents(students);
        return new ResponseEntity<>(savedStudents,HttpStatus.CREATED);
    }

    //DTO for listing all students
    @GetMapping("/api/v1/students")
    public ResponseEntity<Page<StudentResponseDTO>> listAllStudents(Pageable pageable)
    {
        Page<StudentResponseDTO> savedStudentsDTO=service.retreiveAllStudents(pageable);
        return new ResponseEntity<>(savedStudentsDTO,HttpStatus.OK);
    }

    @GetMapping("/api/v1/students/{id}")
    public ResponseEntity<StudentResponseDTO> findById(@PathVariable Integer id)
    {
        StudentResponseDTO studentDTO=service.getStudentById(id);
        return new ResponseEntity<>(studentDTO,HttpStatus.OK);
    }

    @GetMapping("/api/v1/students/search")
    public ResponseEntity<List<StudentResponseDTO>> findByName(@RequestParam String name)
    {
        List<StudentResponseDTO> students=service.getStudentByName(name);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    @PutMapping("/api/v1/students/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Integer id,@RequestBody StudentRequestDTO requestDTO)
    {
        StudentResponseDTO responseDTO=service.updateStudent(id,requestDTO);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/students/{id}")
    public ResponseEntity<StudentResponseDTO> deleteStudent(@PathVariable Integer id)
    {
        StudentResponseDTO deletedStudent=service.deleteStudentById(id);
        return new ResponseEntity<>(deletedStudent,HttpStatus.OK);
    }
}

