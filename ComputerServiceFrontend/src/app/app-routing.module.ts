import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CategoriesComponent } from './categories/categories.component'
import { LoginComponent } from './login/login.component';
import { ProductsComponent } from './products/products.component';
import { CartComponent } from './cart/cart.component';
import { RegisterComponent } from './register/register.component';
import { ProductComponent } from './product/product.component';
import { PurchasesComponent } from './purchases/purchases.component';

const routes: Routes = [
  { path: '', component: CategoriesComponent },
  { path: 'login', component: LoginComponent },
  { path: 'product/:id', component: ProductsComponent },
  { path: 'cart', component: CartComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'showDetails/:id', component: ProductComponent },
  { path: 'purchases', component: PurchasesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
