import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CategoryCrudComponent } from './category-crud/category-crud.component';
import { CategoryComponent } from './category/category.component';
import { IngredientCrudComponent } from './ingredient-crud/ingredient-crud.component';
import { IngredientComponent } from './ingredient/ingredient.component';
import { InstructionCrudComponent } from './instruction-crud/instruction-crud.component';
import { InstructionComponent } from './instruction/instruction.component';
import { MealCrudComponent } from './meal-crud/meal-crud.component';
import { MealTableComponent } from './meal-table/meal-table.component';
import { MediaCrudComponent } from './media-crud/media-crud.component';
import { MediaComponent } from './media/media.component';
import { MenuComponent } from './menu/menu.component';
import { TagCrudComponent } from './tag-crud/tag-crud.component';
import { TagComponent } from './tag/tag.component';

const routes: Routes = [
  // MEAL
  { path: 'meal-crud', component: MealCrudComponent},
  { path: 'meal', component: MealTableComponent},
  // INGREDIENT
  { path: "ingredient", component: IngredientComponent},
  { path: "ingredient-crud", component: IngredientCrudComponent},
  // CATEGORY
  { path: "category", component: CategoryComponent},
  { path: "category-crud", component: CategoryCrudComponent},
  // INSTRUCTION
  { path: "instruction", component: InstructionComponent},
  { path: "instruction-crud", component: InstructionCrudComponent},
  // MEDIA
  { path: "media", component: MediaComponent},
  { path: "media-crud", component: MediaCrudComponent},
  // TAG
  { path: "tag", component: TagComponent},
  { path: "tag-crud", component: TagCrudComponent},
  // MENU
  { path: 'menu', component: MenuComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
