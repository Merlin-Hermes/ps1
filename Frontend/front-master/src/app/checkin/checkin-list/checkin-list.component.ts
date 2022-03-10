import { Component, OnInit } from '@angular/core';
import {CheckinService} from '../../services/checkin.service'
import { Router } from '@angular/router'
import {Checkin} from "../checkin";
import {Cliente} from "../../clientes/cliente";


@Component({
  selector: 'app-checkin-list',
  templateUrl: './checkin-list.component.html',
  styleUrls: ['./checkin-list.component.css']
})
export class CheckinListComponent implements OnInit {

  quartos: Checkin[] = [];
  cliente: Cliente[] = [];
  quartoSelecionado: Checkin;
  messagemSucesso: string;
  messagemError: string;
  clientes: Cliente[] = [];

  constructor(
    private service: CheckinService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.service
      .getQuartos()
      .subscribe(response => this.quartos = response);
  }
  cadastroQuarto(){
    this.router.navigate(['/quarto/form'])
  }

}
