import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersMenubarComponent } from './users-menubar.component';

describe('UsersMenubarComponent', () => {
  let component: UsersMenubarComponent;
  let fixture: ComponentFixture<UsersMenubarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsersMenubarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersMenubarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
