import { Component, OnInit } from '@angular/core';
import {StudentService} from "../service/student.service";
import {Student} from "../service/student";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.less']
})
export class StudentListComponent implements OnInit {

  students: Student[] = [];

  constructor(public studentService: StudentService,
  public router: Router) { }

  ngOnInit(): void {
    this.retrieveAllStudents();
  }

  private retrieveAllStudents() {
    this.studentService.findAll().subscribe(students => {
      console.log(`New data from server! ${students}`);
      this.students = students;
    }, error => {
      console.log(`Error ${error}`);
    });
  }

  delete(id: number) {
    this.studentService.delete(id);
        this.studentService.findAll().subscribe(students => {
          console.log(`New data from server! ${students}`);
          this.students = students;
        }, error => {
          console.log(`Error ${error}`);
        });
  }
}