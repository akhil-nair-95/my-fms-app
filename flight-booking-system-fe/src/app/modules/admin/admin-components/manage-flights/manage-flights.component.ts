import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AirlineDetails } from 'src/app/model/airline-details';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-manage-flights',
  templateUrl: './manage-flights.component.html',
  styleUrls: ['./manage-flights.component.scss']
})
export class ManageFlightsComponent implements OnInit {

  isAdded: boolean = false;
  isAlreadyAvailable: boolean = false;
  //AirLineList:
  airlineList: AirlineDetails[] = [];

  //Form Group:
  flightManageForm: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder, private service: AdminService) { }

  ngOnInit(): void {
    this.initialize();
    this.addAirlineList();
  }

  initialize() {
    this.flightManageForm = this.formBuilder.group({
      airline: new FormControl('', Validators.required),
      flightName: new FormControl('', Validators.required),
      startPlace: new FormControl('', Validators.required),
      endPlace: new FormControl('', Validators.required),
      startDate: new FormControl('', Validators.required),
      endDate: new FormControl('', Validators.required),
      startTime: new FormControl('', Validators.required),
      endTime: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
      totalSeats: new FormControl()
    });
  }

  //Add AirlineList into Form:
  addAirlineList() {
    this.service.getListOfAllAirlines().subscribe(data => {
      this.airlineList = data;
    });
  }

  SheduleFlight() {
    console.log(this.flightManageForm.value);
    this.service.scheduleFlight(this.flightManageForm.value).subscribe(data => {
      if (!!data) {
        console.log('Successfully added')
        this.isAdded = true;
        this.isAlreadyAvailable = false;
        this.flightManageForm.reset();
      } else {
        this.isAlreadyAvailable = true;
        this.isAdded = false;
      }
    }, (err => {
      console.log(err);
      this.isAlreadyAvailable = true;
      this.isAdded = false;
    }))
  }

}
