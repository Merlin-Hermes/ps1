import { Component, OnInit } from '@angular/core';
import {CheckinService} from '../../checkin.service'
import { Router } from '@angular/router'
import {Checkin} from "../checkin";


@Component({
  selector: 'app-checkin-list',
  templateUrl: './checkin-list.component.html',
  styleUrls: ['./checkin-list.component.css']
})
export class CheckinListComponent implements OnInit {

  quartos: Checkin[] = [];
  quartoSelecionado: Checkin;
  messagemSucesso: string;
  messagemError: string;

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
    this.router.navigate(['/quarto-form'])
  }

}
