// Add your models here if you have any
export interface Pizza {
  name: string
  email: string
  size: number
  base: string
  sauce: string
  toppings: string[]
  comments: string
}

export interface Order {
  orderId: number
  price: number
}

export interface Orders {
  email: string
  listOfOrders: Order[]
}
