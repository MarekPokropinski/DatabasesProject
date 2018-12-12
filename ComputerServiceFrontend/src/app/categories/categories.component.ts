import { Component, OnInit } from '@angular/core';
import { CategoriesService } from '../categories.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  constructor(private categoriesService:CategoriesService, private router: Router) { }
  categories;
  ngOnInit() {
    this.categories=this.categoriesService.getRootCategories();
  }
  onCategoryClick(id)  {
    this.router.navigateByUrl(`/product/${id}`);
  }
}
