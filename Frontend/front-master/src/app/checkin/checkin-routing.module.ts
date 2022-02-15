import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CheckinFormComponent} from "./checkin-form/checkin-form.component";
import {CheckinListComponent} from "./checkin-list/checkin-list.component";
import {LayoutComponent} from "../layout/layout.component";

const routes: Routes = [
  {path: 'quarto', component: LayoutComponent, children: [
      {path: 'form', component: CheckinFormComponent},
      {path: 'form/:id', component: CheckinFormComponent},
      {path: 'list', component: CheckinListComponent},
      {path: '', redirectTo: '/quarto/list', pathMatch: 'full'}
    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CheckinRoutingModule { }
