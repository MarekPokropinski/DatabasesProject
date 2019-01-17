import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getProducts(categoryId) {
    return this.http.get(environment.url + 'product/fromCategory?categoryId=' + categoryId).toPromise();
  }
  getProduct(productId) {
    return this.http.get(environment.url + 'product/get?id=' + productId).toPromise();
  }
  addProduct(product) {
    return this.http.post(environment.url + 'product/add', product);
  }
}
