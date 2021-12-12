package ru.ginatulin.Lesson7.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.ginatulin.Lesson7.models.dto.StudentDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "mark")
    private String mark;

    public Student(StudentDto student) {
        this.name = student.getName();
        this.mark = student.getMark();
    }
}
