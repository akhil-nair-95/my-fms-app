import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from '../../components/about/about.component';
import { ContactUsComponent } from '../../components/contact-us/contact-us.component';
import { BookFlightComponent } from './users-components/book-flight/book-flight.component';
import { BookingHistoryComponent } from './users-components/booking-history/booking-history.component';
import { GetUserDetailsComponent } from './users-components/get-user-details/get-user-details.component';
import { UserDashboardComponent } from './users-components/user-dashboard/user-dashboard.component';
import { UserComponent } from './users-components/users-login/users-login.component';

const routes: Routes = [
  { path: "users", component: UserComponent },
  {
    path: "users/dashboard", component: UserDashboardComponent,
    children: [
      { path: "book-flight",  component: BookFlightComponent},
      { path: "enter-details", component: GetUserDetailsComponent},
      { path: "booking-history", component: BookingHistoryComponent}
    ]
  },
  { path: "about", component: AboutComponent },
  { path: "contactus", component: ContactUsComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
