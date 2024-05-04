import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {
    public students: any;
    public dataSource: any;

    public displayedColumns = ['id', 'firstName', 'lastName', 'code', 'programId'];

    constructor(private http: HttpClient) {
    }

    ngOnInit(): void {

        this.http.get("http://localhost:8021/students")
            .subscribe({
                next: data => {

                    this.students = data;
                    this.dataSource = new MatTableDataSource(this.students)

                },
                error: err => {
                    console.log(err);
                }
            })
    }
}

