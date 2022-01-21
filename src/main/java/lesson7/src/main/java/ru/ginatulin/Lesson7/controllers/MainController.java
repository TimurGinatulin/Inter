package ru.ginatulin.Lesson7.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ginatulin.Lesson7.models.dto.StudentDto;
import ru.ginatulin.Lesson7.models.entities.Student;
import ru.ginatulin.Lesson7.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MainController {
    private StudentService service;

    @GetMapping("/get")
    public List<Student> getAll(@RequestParam(required = false) String name) {
        return service.findAllStudents(name);
    }

    @GetMapping("/get/{id}")
    public Student findStudentById(@PathVariable Integer id) {
        return service.findStudentById(id);
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @PutMapping("/update")
    public Student changeStudent(@RequestBody Student student) {
        return service.updateStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
            service.deleteById(id);
    }
}
