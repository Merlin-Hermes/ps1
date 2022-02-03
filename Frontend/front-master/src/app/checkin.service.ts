import { Injectable } from '@angular/core';
import {Checkin} from "./checkin/checkin";
import {Observable} from "rxjs";
import {environment} from '../environments/environment'
import {HttpClient, HttpParams} from "@angular/common/http";
import {CheckinBusca} from "./checkin/checkin-list/checkinBusca";

@Injectable({
  providedIn: 'root'
})
export class CheckinService {

  apiUrl: string =  environment.apiUrlBase + "/api/quartos"

  constructor(private http: HttpClient) { }

  salvar(checkin: Checkin) : Observable<Checkin> {
    return this.http.post<Checkin>(this.apiUrl, checkin);
  }

  buscar(nome: string, mes: number): Observable<CheckinBusca[]> {

    const httpParams = new HttpParams().set("nome", nome).set("mes", mes.toString());
    console.log(httpParams)
    const url = this.apiUrl + "?" + httpParams.toString();
    console.log(url);
    return this.http.get<any>(url);
  }
}
