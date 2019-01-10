import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SessionService } from '../session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private session: SessionService, private router: Router) { }

  user = {
    login: "",
    password: ""
  }

  ngOnInit() {
  }

  async handleLoginButtonClick() {
    this.session.login(this.user.login, this.user.password).subscribe(
      response => {
        this.router.navigateByUrl("/");
      }
    );
  }
}
