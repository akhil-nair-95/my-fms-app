import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightServiceService } from 'src/app/service/flight-service.service';

@Component({
  selector: 'app-users-login',
  templateUrl: './users-login.component.html',
  styleUrls: ['./users-login.component.scss']
})
export class UserComponent implements OnInit {

  // //Form Grroup:
  // loginFormGroup: FormGroup = new FormGroup({});
  // //Login Check:
  // isLoginFail: boolean = false;

  // //Controlls:
  // emailid = new FormControl("admin", [Validators.required]);
  // pswd = new FormControl("admin", Validators.required);

  constructor(private formBuilder: FormBuilder, private router: Router, private fservice: FlightServiceService) { }

  ngOnInit(): void {
    // this.initializationFromBuilder();
  }

  //All Method:
  // initializationFromBuilder() {
  //   this.loginFormGroup = this.formBuilder.group({
  //     username: this.emailid,
  //     password: this.pswd
  //   });
  // }

  // login() {
  //   this.doGeLoginDetails();
  // }

  // token:any;
  // doGeLoginDetails() {
  //   this.fservice.doAuthentication(this.loginFormGroup.value).subscribe(data=>{
  //     if(!!data){
  //       this.token = data;
  //       console.log(this.token.jwttoken);
  //       localStorage.setItem("token", this.token.token);
  //       localStorage.setItem("isUserValid", "true");
  //       sessionStorage.setItem("userName",this.token.userName);
  //       sessionStorage.setItem("emailId", this.loginFormGroup.get('username')?.value);
  //       console.log(localStorage.getItem("token"));
  //     this.forwardRequestToNextPage();
  //     }else{
  //       this.isLoginFail = true;
  //     }
  //   },err=>{
  //     this.isLoginFail = true;
  //     console.log(err);
  //   });
  // }
  

  // forwardRequestToNextPage() {
  //   console.log("ena achuu..!");
  //   this.router.navigate(["/dashboard/searchFlight"]);
  // }

  // signUp() {
  //   this.router.navigate(['users/users-signup']);
  // }

}
