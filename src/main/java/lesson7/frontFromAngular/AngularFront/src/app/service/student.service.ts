import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Student} from "./student";

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(public http: HttpClient) { }

  public findAll() {
    return this.http.get<Student[]>("api/get");
  }

  public findById(id: number) {
    return this.http.get<Student>(`api/get/${id}`);
  }

  public save(student: Student){
    const headers = { 'content-type': 'application/json'};
    const body=JSON.stringify(student);
    this.http.post(`api/add`,body,{'headers':headers})
    .subscribe(student => {
              console.log(student);
            }, error => {
              console.log(`Error ${error}`);
            });
  }
    public update (student: Student){
      const headers = { 'content-type': 'application/json'};
      const body=JSON.stringify(student);
      this.http.put(`api/update`,body,{'headers':headers})
      .subscribe(student => {
                console.log(student);
              }, error => {
                console.log(`Error ${error}`);
              });
    }
    public delete (id: number){
    this.http.delete(`api/delete/${id}`)
    .subscribe();
    }
}