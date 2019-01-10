import { Component, OnInit } from '@angular/core';
import { CategoriesService } from '../categories.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  constructor(private categoriesService: CategoriesService,
    private route: ActivatedRoute,
    private productsService: ProductService,
    private router: Router) {
  }
  id: number;
  categories;
  products;
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.reload(params['id']);
    });
  }
  onCategoryClick(id: number) {
    this.router.navigateByUrl(`/product/${id}`);
  }
  onProductClick(id: number) {
    this.router.navigateByUrl(`/showDetails/${id}`);
  }

  async getLists(id: number) {
    this.categories = await this.categoriesService.getChildren(id);
    if (this.categories.length == 0) {
      this.categories = null;
    }
    this.products = await this.productsService.getProducts(id);
  }
  reload(id: number) {
    this.getLists(id);
  }

}
