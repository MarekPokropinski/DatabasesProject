import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { PurchaseService } from '../purchase.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private cartService: CartService, private purchasesSerivce: PurchaseService) { }

  products;
  totalValue;

  ngOnInit() {
    this.prepareCart();
  }

  async prepareCart() {
    this.products = await this.cartService.getCart();
    this.totalValue = await this.cartService.getCartPrice();
  }

  handleBuyButton() {
    this.purchasesSerivce.createPurchase();
    this.products = [];
  }
  async addProduct(element) {
    element.amount++;
    this.totalValue += element.product.price;
    await this.cartService.addProductToCart(element.product.id, 1);
  }
  async removeProduct(element) {
    element.amount--;
    this.totalValue -= element.product.price;
    if (element.amount <= 0) {
      this.products.splice(
        this.products.indexOf(element),
        1
      )
    }
    await this.cartService.addProductToCart(element.product.id, -1)
  }
}
