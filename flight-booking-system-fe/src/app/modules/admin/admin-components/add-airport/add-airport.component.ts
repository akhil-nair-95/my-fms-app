import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-add-airport',
  templateUrl: './add-airport.component.html',
  styleUrls: ['./add-airport.component.scss']
})
export class AddAirportComponent implements OnInit {

// Form Group:
addAirportForm: FormGroup = new FormGroup({});
isAdded: boolean = false;
errorText: string = "";

  constructor(private formBuilder: FormBuilder, private service: AdminService, private toastrService: ToastrService) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm() {
    this.addAirportForm = new FormGroup({
      //Form Control:
      airportLocation: new FormControl("", Validators.required),
      airportName: new FormControl('', Validators.required)
    });
  }

   //Logics:
   submitAddAirport() {
    console.log(this.addAirportForm.value);
    this.service.addNewAirport(this.addAirportForm.value).subscribe(data => {
      if (data.status == 200) {
        this.toastrService.success('Airport added successfully', 'Success');
        this.isAdded = true;
        this.errorText = ''
        this.addAirportForm.reset();        
      } else {
        this.errorText = 'Airport Data Already Available';
        this.isAdded = false;
      }

    }, (err) => {
      console.log(err);
      this.errorText = 'Error while adding Airport';
      this.isAdded = false;
    });
  }

}
