import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-add-airline',
  templateUrl: './add-airline.component.html',
  styleUrls: ['./add-airline.component.scss']
})
export class AddAirlineComponent implements OnInit {

  // Form Group:
  addAirlineForm: FormGroup = new FormGroup({});
  isAdded: boolean = false;
  errorText: string = "";

  constructor(private formBuilder: FormBuilder, private service: AdminService) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm() {
    this.addAirlineForm = new FormGroup({
      //Form Control:
      name: new FormControl("", Validators.required),
      contactNumber: new FormControl('', Validators.required),
      contactAddress: new FormControl("", Validators.required)
    });
  }

  //Logics:
  submitAddFlight() {
    console.log(this.addAirlineForm.value);
    this.service.addNewAriline(this.addAirlineForm.value).subscribe(data => {
      if (data == true) {
        this.isAdded = true;
        this.errorText = ''
        this.addAirlineForm.reset();        
      } else {
        this.errorText = 'Airline Data Already Available';
        this.isAdded = false;
      }

    }, (err) => {
      console.log(err);
      this.errorText = 'Error while adding Airline data';
      this.isAdded = false;
    });
  }
}
