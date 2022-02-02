import {Cliente} from "../clientes/cliente";

export class Quarto{
  post<T>(apiUrl: string, checkin: import("../checkin/checkin").Checkin): import("rxjs").Observable<import("../checkin/checkin").Checkin> {
    throw new Error("Method not implemented.");
  }
  id: number;
  descricao: String;
  cliente: Cliente;
  valor: String;
  dataCadastro: String;
  status: string;
}
