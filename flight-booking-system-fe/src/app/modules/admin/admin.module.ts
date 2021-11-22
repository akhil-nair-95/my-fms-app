import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AddAirlineComponent } from './admin-components/add-airline/add-airline.component';
import { AdminMenuComponent } from './admin-components/admin-menu/admin-menu.component';
import { AdminLoginComponent } from './admin-components/admin-login/admin-login.component';
import { AdminDashboardComponent } from './admin-components/admin-dashboard/admin-dashboard.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { LoginGuardGuard } from 'src/app/guards/login-guard.guard';
import { ManageFlightsComponent } from './admin-components/manage-flights/manage-flights.component';
import { ManageScheduledFlightsComponent } from './admin-components/manage-scheduled-flights/manage-scheduled-flights.component';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatInputModule } from '@angular/material/input';
// import { ReportsComponent } from './admin-components/reports/reports.component';
import { UserTicketsComponent } from './admin-components/user-tickets/user-tickets.component';
import { AddAirportComponent } from './admin-components/add-airport/add-airport.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import {MatTableModule} from '@angular/material/table';

@NgModule({
  declarations: [AddAirlineComponent, AdminMenuComponent, AdminLoginComponent, AdminDashboardComponent, ManageFlightsComponent, ManageScheduledFlightsComponent, UserTicketsComponent, AddAirportComponent],
  imports: [CommonModule, AdminRoutingModule, ReactiveFormsModule, RouterModule, MatNativeDateModule, MatFormFieldModule,
    MatInputModule, MatRadioModule, MatSelectModule, MatAutocompleteModule, MDBBootstrapModule.forRoot(), MatTableModule],
  providers: [LoginGuardGuard],
  schemas:[NO_ERRORS_SCHEMA]
})
export class AdminModule { }
