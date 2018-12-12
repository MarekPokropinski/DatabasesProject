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
  id:number;
  categories=[];
  products=[];
  ngOnInit() { 
    this.init();   
  }
  onCategoryClick(id)  {
    this.router.navigateByUrl(`/product/${id}`);
    this.init();
    this.getLists(id);
  }
  init()  {
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.getLists(this.id);
  }
  getLists(id){
    this.categories=this.categoriesService.getChildren(id);
    this.products=this.productsService.getProducts(id);  
  }
}
