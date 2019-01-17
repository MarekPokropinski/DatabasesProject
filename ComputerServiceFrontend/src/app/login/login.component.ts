import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SessionService } from '../session.service';
import { MessageService } from 'primeng/api';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private session: SessionService, private router: Router, private messageService: MessageService) { }

  user = {
    login: "",
    password: ""
  }

  ngOnInit() {
  }

  handleLoginButtonClick() {
    this.session.login(this.user.login, this.user.password)
      .subscribe(
        response => {
          this.router.navigateByUrl("/");
        },
        error => {
          this.messageService.add({ severity: 'error', summary: "Wrong username or password" });
        }
      );
  }
}
