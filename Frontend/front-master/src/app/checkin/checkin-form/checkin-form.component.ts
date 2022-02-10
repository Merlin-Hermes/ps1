import { Component, OnInit } from '@angular/core';
import {Cliente} from "../../clientes/cliente";
import {ClientesService} from "../../clientes.service";
import {Checkin} from "../checkin";
import {CheckinService} from "../../checkin.service"
import {Params, Router, ActivatedRoute} from '@angular/router'
import {Observable} from "rxjs";
import {checkThresholds} from "@angular-devkit/build-angular/src/angular-cli-files/utilities/bundle-calculator";
import {error} from "protractor";

@Component({
  selector: 'app-checkin-form',
  templateUrl: './checkin-form.component.html',
  styleUrls: ['./checkin-form.component.css']
})
export class CheckinFormComponent implements OnInit {


  clientes: Cliente[] = [];
  servico: Checkin;
  id: number;
  success: boolean = false;
  successCheck: boolean = false;
  errors: String[] = [];

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
      this.checkservice.checkinQuarto(this.servico).subscribe( response => {
        this.successCheck = true;
        this.errors = null;
        this.servico = response;
      })
    }
  else if (!this.id) {
      this.checkservice
        .salvarQuarto(this.servico)
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
