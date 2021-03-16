import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructionCrudComponent } from './instruction-crud.component';

describe('InstructionCrudComponent', () => {
  let component: InstructionCrudComponent;
  let fixture: ComponentFixture<InstructionCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructionCrudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructionCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
