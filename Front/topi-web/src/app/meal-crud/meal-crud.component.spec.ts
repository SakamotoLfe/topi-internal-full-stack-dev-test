import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MealCrudComponent } from './meal-crud.component';

describe('MealCrudComponent', () => {
  let component: MealCrudComponent;
  let fixture: ComponentFixture<MealCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MealCrudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MealCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
