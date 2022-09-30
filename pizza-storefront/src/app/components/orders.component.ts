import { Component, OnInit } from '@angular/core';
import { Order, Orders } from '../models';
import { PizzaService } from '../pizza.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  _orders!: Orders
  email!: string

  constructor(private pizzaSvc: PizzaService) { }

  ngOnInit(): void {
    this.pizzaSvc.getOrders(this.email).subscribe(v => this._orders = v)
  }

}
