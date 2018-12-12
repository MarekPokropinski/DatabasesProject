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
  onLogin() {
    console.log("login");
    this.router.navigateByUrl('/login');
  }
  authenticated(): boolean {
    return this.session.isAuthenticated();
  }
  goToHomeScreen() {
    this.router.navigateByUrl('/');
  }
  onLogout() {
    this.session.logout();
  }
  goToCart() {
    this.router.navigateByUrl('/cart');
  }
}
