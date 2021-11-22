import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:"admin", loadChildren: ()=> import("./modules/admin/admin.module").then(mod=>mod.AdminModule)},
  {path:"users", loadChildren: ()=> import("./modules/users/users.module").then(mod=>mod.UsersModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
