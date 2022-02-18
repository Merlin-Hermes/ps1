import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientesformComponent } from './clientesform/clientesform.component'
import {ClientesListaComponent} from "./clientes-lista/clientes-lista.component";
import {LayoutComponent} from "../layout/layout.component";

const routes: Routes = [

  {path: 'clientes', component: LayoutComponent, children: [
      {path: 'form', component: ClientesformComponent},
      {path: 'form/:id', component: ClientesformComponent},
      {path: 'lista', component: ClientesListaComponent},
      {path: '', redirectTo: '/clientes/lista', pathMatch: 'full'}
    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
