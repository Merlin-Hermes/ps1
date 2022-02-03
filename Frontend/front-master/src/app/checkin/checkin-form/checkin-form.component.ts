import { Component, OnInit } from '@angular/core';
import {Cliente} from "../../clientes/cliente";
import {ClientesService} from "../../clientes.service";
import {Checkin} from "../checkin";
import {CheckinService} from "../../checkin.service"
import { Router } from '@angular/router'

@Component({
  selector: 'app-checkin-form',
  templateUrl: './checkin-form.component.html',
  styleUrls: ['./checkin-form.component.css']
})
export class CheckinFormComponent implements OnInit {

  clientes: Cliente[] = []
  servico: Checkin;
  success: boolean = false
  errors: String[];

  constructor(
    private checkservice: CheckinService,
    private clienteservice: ClientesService,
    private router: Router
  ) {
    this.servico = new Checkin();
  }

  ngOnInit(): void {
    this.clienteservice
      .getCliente()
      .subscribe(response => this.clientes = response);
  }

  onSubmit(){
    this.checkservice
      .salvar(this.servico)
      .subscribe(Response => {
          this.success = true;
          this.errors = null;
          this.servico = new Checkin();
        }, errorReponse => {
          this.success = false;
          this.errors = errorReponse.error.erros;
        }
      )
  }

  voltar(){
    this.router.navigate(['/quarto-list'])
  }

}
