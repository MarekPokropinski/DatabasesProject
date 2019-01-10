import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from '../session.service';
import { ProductService } from '../product.service';
import { ActivatedRoute } from '@angular/router';

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

  constructor(private session: SessionService, private router: Router, private productsService: ProductService, private route: ActivatedRoute, ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.reload(params['id']);
    });
  }

  async getData(id) {
    this.product = await this.productsService.getProduct(id);
    this.name = this.product.name;
  }

  reload(id) {
    console.log(id);
  }

}
