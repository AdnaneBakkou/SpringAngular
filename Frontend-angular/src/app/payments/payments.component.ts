import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrl: './payments.component.css'
})
export class PaymentsComponent implements  OnInit{
public payments : any ;
public dataSource : any;
public displayedColumns = ['id', 'date', 'amount', 'type', 'status', 'firstName' ,'code'];  // Correct the typo here


  constructor(private http : HttpClient) {
  }

  ngOnInit(): void {

    this.http.get("http://localhost:8021/payments")
      .subscribe({
        next : data => {

          this.payments = data;
          this.dataSource = new MatTableDataSource(this.payments)

        },
        error : err => {
          console.log(err);
        }
      })
  }



}
