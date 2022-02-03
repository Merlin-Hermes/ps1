import { Component, OnInit } from '@angular/core';
import {Cliente} from "../../clientes/cliente";
import {ClientesService} from "../../clientes.service";
import {Checkin} from "../checkin";
import {CheckinService} from "../../checkin.service"
import {Params, Router, ActivatedRoute} from '@angular/router'
import {Observable} from "rxjs";

@Component({
  selector: 'app-checkin-form',
  templateUrl: './checkin-form.component.html',
  styleUrls: ['./checkin-form.component.css']
})
export class CheckinFormComponent implements OnInit {


  clientes: Cliente[] = []
  servico: Checkin;
  id: number
  success: boolean = false
  errors: String[];

  constructor(
    private checkservice: CheckinService,
    private clienteservice: ClientesService,
    private router: Router,
    private routerActivated: ActivatedRoute
  ) {
    this.servico = new Checkin();
  }

  ngOnInit(): void {
    this.clienteservice
      .getCliente()
      .subscribe(response => this.clientes = response);

    let params : Observable<Params> = this.routerActivated.params
    params.subscribe(urlParams => {
      this.id = urlParams['id'];
      if (this.id){
        this.checkservice
          .getQuartosById(this.id)
          .subscribe(Response => this.servico = Response,
              errorResponse => this.servico = new Checkin())
      }
    })
  }

  onSubmit(){
    if (this.id) {
      this.checkservice
        .checkinQuarto(this.servico)
        .subscribe(Response =>{
          this.success = true;
          this.errors = null;
        }, errorReponse => {
          this.errors = ['error ao atualizar o cliente.']
        })
    }
    else {
      this.checkservice
        .salvar(this.servico)
        .subscribe(response => {
            this.success = true;
            this.errors = null;
            this.servico = response;
          }, errorReponse => {
            this.success = false;
            this.errors = errorReponse.error.erros;
          }
        )
    }
  }

  voltar(){
    this.router.navigate(['/quarto-list'])
  }

}
