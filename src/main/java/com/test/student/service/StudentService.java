package com.test.student.service;

import com.test.student.dto.StudentRequestDTO;
import com.test.student.dto.StudentResponseDTO;
import com.test.student.entity.Student;
import com.test.student.exception.EmailAlreadyExistsException;
import com.test.student.exception.ResourceNotFoundException;
import com.test.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class StudentService {

    @Autowired
    StudentRepository repo;
    private StudentResponseDTO mapToResponse(Student student,String message)
    {
        StudentResponseDTO responseDTO=new StudentResponseDTO();
        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setStatus(message);
        responseDTO.setResponseTimeStamp(LocalDateTime.now());
        return responseDTO;
    }

    public StudentResponseDTO createNewStudent(StudentRequestDTO dto) {
        if(repo.existsByEmail(dto.getEmail()))
        {
            throw new EmailAlreadyExistsException("A student with email "+ dto.getEmail()+" already exists");
        }
        Student student=new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        Student newStudent=repo.save(student);
        StudentResponseDTO newResponseDTO=new StudentResponseDTO();
        newResponseDTO.setId(newStudent.getId());
        newResponseDTO.setName(newStudent.getName());
        newResponseDTO.setEmail(newStudent.getEmail());
        newResponseDTO.setStatus("Student created");
        newResponseDTO.setResponseTimeStamp(LocalDateTime.now());
        return newResponseDTO;
    }

    @Transactional
    public List<StudentResponseDTO> createStudents(List<Student> students) {
        List<Student> studentsCreated= repo.saveAll(students);
        List<StudentResponseDTO> finalStudents=new ArrayList<>();
        for(Student s: studentsCreated)
        {
            StudentResponseDTO finalStudent=mapToResponse(s,"Student succesfully added");
            finalStudents.add(finalStudent);
        }
        return finalStudents;
    }

//    public Pageable<StudentResponseDTO> retreiveAllStudents() {
//        Pageable<Student> studentsList=repo.findAll();
//        Pageable<StudentResponseDTO> studentResponseDTOList=new P();
//        for (Student s: studentsList)
//        {
//            StudentResponseDTO targetStudent=mapToResponse(s,"student retreived and transformes resonse DTO");
//            studentResponseDTOList.add(targetStudent);
//        }
//        return studentResponseDTOList;
//    }

    public Page<StudentResponseDTO> retreiveAllStudents(Pageable pageable) {
        // 1. Fetch the 'Page' of entities from the repository
        Page<Student> studentsPage = repo.findAll(pageable);

        // 2. Transform the Page of Entities into a Page of DTOs
        return studentsPage.map(student ->
                mapToResponse(student, "Student retrieved and transformed to DTO")
        );
    }

    public StudentResponseDTO getStudentById(Integer id) {

        Student student=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found with id "+id));
        StudentResponseDTO responseDTO=mapToResponse(student,"Student Found Succesfully");
        return responseDTO;
    }

    public List<StudentResponseDTO> getStudentByName(String name) {
        List<Student> students=repo.findAllByName(name);
        List<StudentResponseDTO> finalStudentsList=new ArrayList<>();

        for(Student s: students)
        {
            StudentResponseDTO finalStudent=mapToResponse(s,"Student found by name "+name);
            finalStudentsList.add(finalStudent);
        }
        return finalStudentsList;
    }

    @Transactional
    public StudentResponseDTO updateStudent(Integer id, StudentRequestDTO requestDTO) {

        Student existingStudent = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Update Failed: No student found with ID " + id));

        existingStudent.setId(id);
        existingStudent.setName(requestDTO.getName());
        existingStudent.setAge(requestDTO.getAge());
        existingStudent.setEmail(requestDTO.getEmail());
        Student updatedStudent=repo.save(existingStudent);

        return mapToResponse(existingStudent,"Student succesfully modified..");
    }

    //DTO for delete mapping
    public StudentResponseDTO deleteStudentById(Integer id) {
        Student student=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Delete Failed: No student found with ID " + id));
        repo.deleteById(id);

        return mapToResponse(student,"Student deleted");
    }
}