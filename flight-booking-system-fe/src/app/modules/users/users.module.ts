import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsersRoutingModule } from './users-routing.module';
import { BookingHistoryComponent } from './users-components/booking-history/booking-history.component';
import { BookFlightComponent } from './users-components/book-flight/book-flight.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UsersMenubarComponent } from './users-components/users-menubar/users-menubar.component';
import { UserDashboardComponent } from './users-components/user-dashboard/user-dashboard.component';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { GetUserDetailsComponent } from './users-components/get-user-details/get-user-details.component';
// import { PaymentGatewayComponent } from './users-components/payment-gateway/payment-gateway.component';
// import { PaymentSuccessComponent } from './users-components/payment-success/payment-success.component';
import { UserComponent } from './users-components/users-login/users-login.component';
// import { UsersLoginGuard } from 'src/app/guards/users-login.guard';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import {MatTableModule} from '@angular/material/table';

@NgModule({
  declarations: [
    BookingHistoryComponent,
    BookFlightComponent,
    UsersMenubarComponent,
    UserDashboardComponent,
    GetUserDetailsComponent,
    // PaymentGatewayComponent,
    // PaymentSuccessComponent,
    UserComponent
  ],
  imports: [CommonModule, UsersRoutingModule, ReactiveFormsModule, FormsModule, MatNativeDateModule, MatFormFieldModule,
    MatInputModule, MatRadioModule, MatSelectModule, MatAutocompleteModule, MDBBootstrapModule.forRoot(), MatTableModule],
  // providers: [UsersLoginGuard]
  schemas:[NO_ERRORS_SCHEMA],
  providers:[]
})
export class UsersModule { }
