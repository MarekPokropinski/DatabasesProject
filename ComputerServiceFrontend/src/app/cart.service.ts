import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  addProductToCart(id: number, amount: number) {
    return this.http.put(environment.url + `cart/add?productId=${id}&amount=${amount}`, {}).toPromise();
  }
  getCart() {
    return this.http.get(environment.url + 'cart/get').toPromise();
  }
}
