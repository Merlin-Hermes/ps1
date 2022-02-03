import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CheckinFormComponent} from "./checkin-form/checkin-form.component";
import {CheckinListComponent} from "./checkin-list/checkin-list.component";


const routes: Routes = [
  {path: 'checkin-form', component: CheckinFormComponent},
  {path: 'quarto-list', component: CheckinListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CheckinRoutingModule { }
