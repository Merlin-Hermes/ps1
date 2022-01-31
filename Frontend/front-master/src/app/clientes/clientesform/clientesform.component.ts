import { Component, OnInit } from '@angular/core';

import {Router, ActivatedRoute, Params} from '@angular/router'
import { Cliente } from '../cliente';
import {ClientesService} from '../../clientes.service'
import {error} from "protractor";
import {Observable} from "rxjs";


@Component({
  selector: 'app-clientesform',
  templateUrl: './clientesform.component.html',
  styleUrls: ['./clientesform.component.css']
})
export class ClientesformComponent implements OnInit {

  cliente: Cliente;
  success: boolean = false
  errors: String[];
  id: number;

  constructor
  (
    private service: ClientesService,
    private router: Router,
    private activadeRouter: ActivatedRoute
  ) {
    this.cliente = new Cliente();

   }

  ngOnInit(): void {
   let params : Observable<Params> = this.activadeRouter.params
    params.subscribe(urlParams => {
      this.id = urlParams['id'];
      if (this.id){
        this.service
          .getClienteById(this.id)
          .subscribe(Response => this.cliente = Response, errorResponse => this.cliente = new Cliente())
      }
    })

  }

  voltarListagem(){
    this.router.navigate(['/clientes-lista'])
  }

  onSubmit() {
    if (this.id) {

      this.service
        .atualizar(this.cliente)
        .subscribe(Response =>{
          this.success = true;
          this.errors = null;
        }, errorReponse => {
          this.errors = ['error ao atualizar o cliente.']
          })
    } else {

      this.service
        .salvar(this.cliente)
        .subscribe(Response => {
            this.success = true;
            this.errors = null;
            this.cliente = Response;
          }, errorReponse => {
            this.success = false;
            this.errors = errorReponse.error.erros;
          }
        )

    }
  }

}
