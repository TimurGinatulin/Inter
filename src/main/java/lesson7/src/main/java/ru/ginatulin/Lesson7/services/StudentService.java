package ru.ginatulin.Lesson7.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ginatulin.Lesson7.models.dto.StudentDto;
import ru.ginatulin.Lesson7.models.entities.Student;
import ru.ginatulin.Lesson7.repositories.StudentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository repository;

    public List<Student> findAllStudents(String name) {
        if (name == null)
            return repository.findAll();
        else
            return repository.findByName(name);
    }

    public Student findStudentById(Integer id) {
        return repository.findById(id).orElse(new Student(-1, "non", "non"));
    }

    public Student addStudent(StudentDto student) {
        return repository.save(new Student(student));
    }

    public Student updateStudent(Student student) {
        return repository.save(student);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
