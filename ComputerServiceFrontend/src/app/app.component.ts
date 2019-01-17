import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from './session.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ComputerServiceFrontend';
  constructor(private router: Router, private session: SessionService) {
  }
  isAdmin = false;
  onLogin() {
    console.log("login");
    this.router.navigateByUrl('/login');
  }
  authenticated(): boolean {
    this.isAdmin = this.session.getLogin() === "admin";
    return this.session.isAuthenticated();
  }
  goToHomeScreen() {
    this.router.navigateByUrl('/');
  }
  onLogout() {
    this.session.logout();
    this.router.navigateByUrl('/');
  }
  goToCart() {
    this.router.navigateByUrl('/cart');
  }
  goToPurchases() {
    this.router.navigateByUrl('/purchases');
  }
  goToAdmin() {
    this.router.navigateByUrl('/admin');
  }
}
