import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from '../../components/about/about.component';
import { ContactUsComponent } from '../../components/contact-us/contact-us.component';
// import { AboutComponent } from 'src/app/components/about/about.component';
// import { ContactUsComponent } from 'src/app/components/contact-us/contact-us.component';
// import { UsersLoginGuard } from 'src/app/guards/users-login.guard';
import { BookFlightComponent } from './users-components/book-flight/book-flight.component';
import { BookingHistoryComponent } from './users-components/booking-history/booking-history.component';
import { GetUserDetailsComponent } from './users-components/get-user-details/get-user-details.component';
// import { PaymentGatewayComponent } from './users-components/payment-gateway/payment-gateway.component';
// import { PaymentSuccessComponent } from './users-components/payment-success/payment-success.component';
import { UserDashboardComponent } from './users-components/user-dashboard/user-dashboard.component';
import { UserComponent } from './users-components/users-login/users-login.component';

const routes: Routes = [
  { path: "users", component: UserComponent },
  {
    path: "users/dashboard", component: UserDashboardComponent,
    children: [
      // { path: "searchFlight", component: BookFlightComponent},
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
