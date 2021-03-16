import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IngredientCrudComponent } from './ingredient-crud.component';

describe('IngredientCrudComponent', () => {
  let component: IngredientCrudComponent;
  let fixture: ComponentFixture<IngredientCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IngredientCrudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IngredientCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
