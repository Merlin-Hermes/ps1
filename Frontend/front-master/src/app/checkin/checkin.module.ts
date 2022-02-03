import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'

import { CheckinRoutingModule } from './checkin-routing.module';
import { CheckinFormComponent } from './checkin-form/checkin-form.component';
import { CheckinListComponent } from './checkin-list/checkin-list.component';
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [CheckinFormComponent, CheckinListComponent],
  imports: [
    CommonModule,
    CheckinRoutingModule,
    RouterModule,
    FormsModule
  ], exports :[
    CheckinFormComponent,
    CheckinListComponent
  ]
})
export class CheckinModule { }
