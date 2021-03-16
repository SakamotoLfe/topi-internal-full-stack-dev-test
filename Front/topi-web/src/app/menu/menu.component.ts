import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  constructor() { }

  itens = [
    { name: "Meals", description: "Meals that the system have", table: "meal", crud: "meal-crud" },
    { name: "Ingredients", description: "Ingredients that the system have", table: "ingredient", crud: "ingredient-crud" },
    { name: "Categories", description: "Categories that the system have", table: "category", crud: "category-crud" },
    { name: "instructions", description: "instructions that the system have", table: "instruction", crud: "instruction-crud" },
    { name: "Medias", description: "Medias that the system have", table: "media", crud: "media-crud" },
    { name: "Tags", description: "Tags that the system have", table: "tag", crud: "tag-crud" },
  ]

  ngOnInit(): void {
  }

}
