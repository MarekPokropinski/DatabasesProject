import { Component, OnInit } from '@angular/core';
import { SessionService } from '../session.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private session: SessionService, private router: Router, private messageService: MessageService) { }
  user = {
    login: "",
    password: ""
  }
  ngOnInit() {
  }
  handleRegisterButtonClick() {
    let subscription = this.session.register(this.user.login, this.user.password);
    console.log('register');
    subscription
      .pipe(catchError(error => { return throwError(error.error.message) }))
      .subscribe(
        response => this.router.navigateByUrl("/login"),
        error => {
          console.log(error);
          this.messageService.add({ severity: 'error', summary: error });
        }
      );
  }
}
