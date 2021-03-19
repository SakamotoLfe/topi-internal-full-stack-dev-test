import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';

@Component({
  selector: 'app-meal-table',
  templateUrl: './meal-table.component.html',
  styleUrls: ['./meal-table.component.scss']
})
export class MealTableComponent implements OnInit {
  displayedColumns = ['id', 'name', 'area', 'category', 'actions'];
  dataSource = ELEMENT_DATA;

  filter = '';

  meals = [];

  constructor(public service: AppService) { }

  ngOnInit(): void {
    this.service.GET('meals').toPromise().then((result) => {
      this.meals = result.content;
    });
  }

  keyPressed(event: any) {
    if (event.keyCode === 13){
      this.service.GET(`meals?search=START name?${this.filter}`).toPromise().then((result) => {
        this.meals = result.content;
      });
    }
  }
}

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];