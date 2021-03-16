import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MediaCrudComponent } from './media-crud.component';

describe('MediaCrudComponent', () => {
  let component: MediaCrudComponent;
  let fixture: ComponentFixture<MediaCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MediaCrudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MediaCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
