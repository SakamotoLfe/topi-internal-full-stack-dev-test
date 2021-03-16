import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { MealTableComponent } from './meal-table/meal-table.component';
import { MealCrudComponent } from './meal-crud/meal-crud.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { IngredientComponent } from './ingredient/ingredient.component';
import { IngredientCrudComponent } from './ingredient-crud/ingredient-crud.component';
import { CategoryComponent } from './category/category.component';
import { CategoryCrudComponent } from './category-crud/category-crud.component';
import { InstructionComponent } from './instruction/instruction.component';
import { InstructionCrudComponent } from './instruction-crud/instruction-crud.component';
import { MediaComponent } from './media/media.component';
import { MediaCrudComponent } from './media-crud/media-crud.component';
import { TagComponent } from './tag/tag.component';
import { TagCrudComponent } from './tag-crud/tag-crud.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    MealTableComponent,
    MealCrudComponent,
    IngredientComponent,
    IngredientCrudComponent,
    CategoryComponent,
    CategoryCrudComponent,
    InstructionComponent,
    InstructionCrudComponent,
    MediaComponent,
    MediaCrudComponent,
    TagComponent,
    TagCrudComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatMenuModule,
    MatCardModule,
    MatToolbarModule,
    MatButtonModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
