import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CategoriesComponent } from './categories/categories.component'
import { LoginComponent } from './login/login.component';
import { CartComponent } from './cart/cart.component';

const routes: Routes = [
  { path: '', component: CategoriesComponent },
  { path: 'login', component: LoginComponent },
  { path: 'cart', component: CartComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
