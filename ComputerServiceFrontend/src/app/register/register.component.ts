import { Component, OnInit } from '@angular/core';
import { SessionService } from '../session.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private session: SessionService, private router: Router) { }
  user = {
    login: "",
    password: ""
  }
  ngOnInit() {
  }
  handleRegisterButtonClick() {
    this.session.register(this.user.login, this.user.password);
    this.router.navigateByUrl("/login");
  }
}
