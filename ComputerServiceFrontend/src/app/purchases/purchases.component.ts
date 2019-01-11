import { Component, OnInit } from '@angular/core';
import { PurchaseService } from '../purchase.service';

@Component({
  selector: 'app-purchases',
  templateUrl: './purchases.component.html',
  styleUrls: ['./purchases.component.css']
})
export class PurchasesComponent implements OnInit {

  constructor(private purchasesService: PurchaseService) { }

  purchases;

  ngOnInit() {
    this.getPurchases();
  }
  async getPurchases() {
    this.purchases = await this.purchasesService.getPurchases();
    console.log(this.purchases);
  }

}
