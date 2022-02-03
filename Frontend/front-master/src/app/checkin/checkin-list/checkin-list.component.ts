import { Component, OnInit } from '@angular/core';
import {CheckinService} from '../../checkin.service'
import {Quarto} from "../quarto";
import { Router } from '@angular/router'


@Component({
  selector: 'app-checkin-list',
  templateUrl: './checkin-list.component.html',
  styleUrls: ['./checkin-list.component.css']
})
export class CheckinListComponent implements OnInit {

  quartos: Quarto[] = [];
  quartoSelecionado: Quarto;
  messagemSucesso: string;
  messagemError: string;

  constructor(
    private service: CheckinService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
  }
  cadastroQuarto(){
    this.router.navigate(['/checkin-form'])
  }

}
