import { Injectable } from '@angular/core';
import {Checkin} from "./checkin/checkin";
import {Observable} from "rxjs";
import {environment} from '../environments/environment'
import {HttpClient, HttpParams} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class CheckinService {

  apiUrl: string =  environment.apiUrlBase + "/api/quartos"

  constructor(private http: HttpClient) { }

  salvar(checkin: Checkin) : Observable<Checkin> {
    return this.http.post<Checkin>(this.apiUrl, checkin);
  }

  getQuartos(): Observable<Checkin[]> {
    return this.http.get<Checkin[]>(this.apiUrl)
  }

  getQuartosById(id: number): Observable<Checkin>{
    return this.http.get<any>(`${this.apiUrl}/${id}`)
  }

  checkinQuarto(checkin: Checkin): Observable<any> {
    return this.http.put<Checkin>(`${this.apiUrl}/${checkin.id}`, checkin)
  }

}
