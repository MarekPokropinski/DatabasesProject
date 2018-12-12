import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor() { }

  getProducts(categoryId){
    return [
      {
        price: 10,
        name: "Komputery"
      },
      {
        price: 21,
        name: "Telefony"
      }
    ]
  
  }
}
