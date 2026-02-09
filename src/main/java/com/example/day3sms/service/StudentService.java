package com.example.day3sms.service;

import com.example.day3sms.dto.StudentRequestDTO;
import com.example.day3sms.dto.StudentResponseDTO;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public StudentResponseDTO addStudent(StudentRequestDTO dto) {

        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student);

        return new StudentResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );
    }

    // READ
    public List<StudentResponseDTO> getAllStudents() {
        return repository.findAll()
                .stream()
                .map(s -> new StudentResponseDTO(
                        s.getId(),
                        s.getName(),
                        s.getAge(),
                        s.getEmail()
                ))
                .toList();
    }

    // UPDATE (PUT - full update)
    public StudentResponseDTO updateStudent(String id, StudentRequestDTO dto) {

        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existingStudent.setName(dto.getName());
        existingStudent.setAge(dto.getAge());
        existingStudent.setEmail(dto.getEmail());

        StudentModel updated = repository.save(existingStudent);

        return new StudentResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getAge(),
                updated.getEmail()
        );
    }

    // PATCH (partial update)
    public StudentResponseDTO patchStudent(String id, StudentRequestDTO dto) {

        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (dto.getName() != null) {
            existingStudent.setName(dto.getName());
        }

        if (dto.getAge() != null) {
            existingStudent.setAge(dto.getAge());
        }

        if (dto.getEmail() != null) {
            existingStudent.setEmail(dto.getEmail());
        }

        StudentModel updated = repository.save(existingStudent);

        return new StudentResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getAge(),
                updated.getEmail()
        );
    }

    // DELETE
    public void deleteStudent(String id) {
        repository.deleteById(id);
    }
}
