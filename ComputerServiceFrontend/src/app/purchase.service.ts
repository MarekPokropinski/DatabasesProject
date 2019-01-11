import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  constructor(private http: HttpClient) { }

  getPurchases() {
    return this.http.get(environment.url + 'purchase/getPurchases').toPromise();
  }
  createPurchase() {
    return this.http.post(environment.url + 'purchase/createPurchase', {}).toPromise();
  }
}
