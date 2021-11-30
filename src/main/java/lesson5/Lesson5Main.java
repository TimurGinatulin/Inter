package lesson5;

import java.util.List;
import java.util.Random;

public class Lesson5Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        Student student = Student.builder()
                .name("Ivan")
                .mark("4")
                .build();
        studentDao.save(student);
        List<Student> studentList = studentDao.findAll();
        System.out.println(studentList.get(0).toString());
        studentList.get(0).setMark("3");
        studentDao.update(studentList.get(0));
        studentDao.delete(studentList.get(0));
        Random random = new Random();
        String [] nameArr = {"Jon","Bob","David","Lucas"};
        for (int i = 0; i < 100000; i++) {
            Student studentNew = Student.builder()
                    .name(nameArr[random.nextInt(nameArr.length)])
                    .mark(String.valueOf(1 + random.nextInt(5)))
                    .build();
            studentDao.save(studentNew);
        }

    }
}
