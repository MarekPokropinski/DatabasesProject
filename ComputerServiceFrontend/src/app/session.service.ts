import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private http: HttpClient) { }

  login(login: string, password: string) {
    let credentials = 'Basic ' + btoa(login + ':' + password);
    localStorage.setItem("credentials", credentials);

    return this.http.get(`${environment.url}auth/user`).pipe(
      map(response => {
        localStorage.setItem("login", login);
      }),
      catchError(error => {
        localStorage.removeItem("credentials");
        return throwError(error.error.message)
      })
    );
  }
  logout() {
    localStorage.removeItem("credentials");
    localStorage.removeItem("login");
  }
  getLogin(): string {
    return localStorage.getItem("login");
  }
  isAuthenticated() {
    if (localStorage.getItem("login")) {
      return true;
    }
    return false;
  }

  register(user: string, password: string) {
    return this.http.post(environment.url + "user/register", {
      password: password,
      username: user
    });
  }

}
