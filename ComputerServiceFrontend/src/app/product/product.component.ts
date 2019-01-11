import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { SessionService } from '../session.service';
import { ProductService } from '../product.service';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../cart.service';
import { SessionService } from '../session.service';
import { parseCookieValue } from '@angular/common/src/cookie';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  //categoryID: number
  product;
  name: string;
  id: number;
  amount: string;

  constructor(private cartService: CartService, private router: Router, private productsService: ProductService, private route: ActivatedRoute, private session: SessionService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.getData(params['id']);
    });
  }

  async getData(id) {
    this.product = await this.productsService.getProduct(id);
    this.name = this.product.name;
    this.id = id;
  }

  addToCart() {
    let val = parseInt(this.amount);
    if (val != NaN) {
      this.cartService.addProductToCart(this.id, val);
    } this.router.navigateByUrl("/");
  }

  isAuthenticated() {
    return this.session.isAuthenticated();
  }
}
