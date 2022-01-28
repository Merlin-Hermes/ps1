import { Component, OnInit } from '@angular/core';

import { Cliente } from '../cliente';
import {ClientesService} from '../../clientes.service'


@Component({
  selector: 'app-clientesform',
  templateUrl: './clientesform.component.html',
  styleUrls: ['./clientesform.component.css']
})
export class ClientesformComponent implements OnInit {

  cliente: Cliente;
  success: boolean = false
  errors: String[];

  constructor( private service: ClientesService ) {
    this.cliente = new Cliente();

   }

  ngOnInit(): void {

  }

  onSubmit(){
    this.service
    .salvar(this.cliente)
    .subscribe(Response => {
      this.success = true;
    }, errorReponse => {
      this.errors = errorReponse.error.errors;
      }
    )
  }

}
