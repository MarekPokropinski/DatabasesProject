import { Component, OnInit } from '@angular/core';
import { CategoriesService } from '../categories.service';
import { ProductService } from '../product.service';
import { MessageService } from 'primeng/api';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private categoriesService: CategoriesService, private productService: ProductService, private messageService: MessageService) { }

  categoriesLists = [];
  selectedCategory = null;
  buttonDisabled = null;
  price: string = "";
  name = "";
  description = "";


  ngOnInit() {
    this.prepareCategories();
  }

  async prepareCategories() {
    let categories = await this.categoriesService.getRootCategories();
    this.categoriesLists = [{ list: categories, selected: categories[0], disabled: null }];
  }

  categoryChanged(category) {
    let index = this.categoriesLists.findIndex(category);

  }
  async addCategory() {
    let id = this.categoriesLists[this.categoriesLists.length - 1].selected.id;
    this.categoriesLists[this.categoriesLists.length - 1].disabled = true;
    let categories = await this.categoriesService.getChildren(id);
    if (categories.length !== 0) {
      this.categoriesLists.push({ list: categories, selected: categories[0], disabled: null });
    }
  }
  addProduct() {
    let price1 = this.price.replace(",", ".");
    let price: number = parseFloat(price1);
    if (isNaN(price) || price < 0) {
      console.log('error')
      this.messageService.add({ severity: 'error', summary: "Zły format ceny" });
      return;
    }
    let id = this.categoriesLists[this.categoriesLists.length - 1].selected.id;
    let priceStr = price.toFixed(2);
    if (priceStr.length < price1.length) {
      console.log('error')
      this.messageService.add({ severity: 'error', summary: "Zły format ceny" });
      return;
    }
    this.productService.addProduct({
      categoryId: id,
      name: this.name,
      description: this.description,
      price: priceStr
    }).pipe(
      catchError(error => {
        return throwError(error.error.message);
      })
    )
      .subscribe(
        resp => {
          this.messageService.add({ severity: 'success', summary: "Dodano nowy produkt" });
          this.reset();
        },
        error => {
          this.messageService.add({ severity: 'error', summary: error });
        }
      );
  }
  reset() {
    this.categoriesLists = [];
    this.selectedCategory = null;
    this.buttonDisabled = null;
    this.price = "";
    this.name = "";
    this.description = "";
  }

}
