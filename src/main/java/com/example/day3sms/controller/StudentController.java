package com.example.day3sms.controller;

import com.example.day3sms.dto.StudentRequestDTO;
import com.example.day3sms.dto.StudentResponseDTO;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/add-student")
    public StudentResponseDTO addStudent(@Valid @RequestBody StudentRequestDTO student){
        return service.addStudent(student);
    }

    @GetMapping("/students")
    public List<StudentResponseDTO> getAllStudents(){
        return service.getAllStudents();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id){
        service.deleteStudent(id);
        return "Student Deleted Successfully";
    }

    @PutMapping("/update/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable String id,
            @Valid @RequestBody StudentRequestDTO student) {

        return service.updateStudent(id, student);
    }

    // ✅ PATCH – partial update
    @PatchMapping("/patch/{id}")
    public StudentResponseDTO patchStudent(
            @PathVariable String id,
            @RequestBody StudentRequestDTO student) {

        return service.patchStudent(id, student);
    }

}
