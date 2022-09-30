import { Component, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Pizza } from '../models';
import { PizzaService } from '../pizza.service';

const SIZES: string[] = [
  "Personal - 6 inches",
  "Regular - 9 inches",
  "Large - 12 inches",
  "Extra Large - 15 inches"
]

const PizzaToppings: string[] = [
    'chicken', 'seafood', 'beef', 'vegetables',
    'cheese', 'arugula', 'pineapple'
]

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})

export class MainComponent implements OnInit {

  pizzaForm!: FormGroup

  pizzaSize = SIZES[0]

  constructor(private fb: FormBuilder, private router: Router, private pizzaSvc: PizzaService) {}

  ngOnInit(): void {
    this.pizzaForm = this.createForm()
  }

  createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      email: this.fb.control<string>('', [Validators.required]),
      size: this.fb.control<number>(0, [Validators.required]),
      base: this.fb.control<string>('', [Validators.required]),
      sauce: this.fb.control<string>('', [Validators.required]),
      toppings: this.fb.array([this.fb.control<string>('', [Validators.required])]),
      comments: this.fb.control<string>('')
    })
  }

  updateSize(size: string) {
    this.pizzaSize = SIZES[parseInt(size)]
  }

  submitForm() {
    const data = this.pizzaForm.value as Pizza
    console.log(':::::DATA: ', data)
    this.pizzaSvc.createOrder(data)
    const input = this.pizzaForm.get('email')?.value
    console.log(":::::INPUT: ", input)
    this.router.navigate([`/api/orders/${input}/all`])
  }

  toOrdersPage() {
    const input = this.pizzaForm.get('email')?.value
    console.log(":::::INPUT: ", input)
    this.router.navigate([`/api/orders/${input}/all`])
  }

}
