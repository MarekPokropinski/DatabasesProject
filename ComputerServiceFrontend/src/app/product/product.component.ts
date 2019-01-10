import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { SessionService } from '../session.service';
import { ProductService } from '../product.service';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  //categoryID: number
  product;
  name: string
  id: number

  constructor(private cartService: CartService, private router: Router, private productsService: ProductService, private route: ActivatedRoute, ) { }

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
    console.log(this.id)
    this.cartService.addProductToCart(this.id, 1);
    this.router.navigateByUrl("/");
  }
}
