import { Component} from '@angular/core';
import {Router} from "@angular/router";
import {Usuario} from "./Usuario"
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {

  username: string;
  password: string;
  messagemSucess: string;
  cadastrando: boolean;
  errors: String[];

  constructor(
    private router: Router,
    private authService: AuthService
  ) {

  }

  onSubmit(){
    this.authService.tentarLogar(this.username, this.password).subscribe(
      response => {
        this.router.navigate(['/home'])
      }, error => {
        this.errors = ['Erro']
      })

  }

  prepararCadastro(event){
    event.preventDefault();
    this.cadastrando = true;
  }

  cancelarCadastro(event){
    event.preventDefault();
    this.cadastrando = false;
  }

  cadastrar(){
    const usuario: Usuario = new Usuario();
    usuario.username = this.username;
    usuario.password = this.password;
    this.authService.salvar(usuario).subscribe(response => {
      this.messagemSucess = "cadastrado com sucesso"
      this.cadastrando = false;
      this.username = '';
      this.password = '';
      this.errors = [];
    }, errorResponse => {
      this.messagemSucess = null;
      this.errors = errorResponse.error.erros;
    })
  }

}
