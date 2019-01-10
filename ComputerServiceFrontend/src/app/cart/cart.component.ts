import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private cartService: CartService) { }

  private products;
  private totalValue;

  ngOnInit() {
    this.prepareCart();
  }

  async prepareCart() {
    this.products = await this.cartService.getCart();
  }

  handleBuyButton() {

  }
  async addProduct(element) {
    await this.cartService.addProductToCart(element.product.id, 1)
    this.prepareCart();
  }
  async removeProduct(element) {
    await this.cartService.addProductToCart(element.product.id, -1)
    this.prepareCart();
  }
}
