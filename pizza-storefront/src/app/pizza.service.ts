// Implement the methods in PizzaService for Task 3
// Add appropriate parameter and return type

import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Observable } from "rxjs";
import { Order, Orders, Pizza } from "./models";

@Injectable()
export class PizzaService {

  constructor(private http: HttpClient) { }

  // POST /api/order
  // Add any required parameters or return type
  createOrder(pizza: Pizza) {
    const headers = new HttpHeaders()
    .set('Content-Type', 'application/json')
    .set('Accept', 'application/json')

    return firstValueFrom(
      this.http.post<any>('/api/order', pizza, { headers })
    )
  }

  // GET /api/order/<email>/all
  // Add any required parameters or return type
  getOrders(email: string): Observable<Orders> {
    return this.http.get<Orders>(`/api/order/${email}/all`)
  }

}
