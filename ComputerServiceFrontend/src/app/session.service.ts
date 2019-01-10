import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { registerContentQuery } from '@angular/core/src/render3';
import { stringify } from '@angular/core/src/util';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private http: HttpClient) { }

  login(login: string, password: string) {
    let credentials = 'Basic ' + btoa(login + ':' + password);
    localStorage.setItem("credentials", credentials);
    localStorage.setItem("login", login);
  }
  logout() {
    localStorage.removeItem("credentials");
    localStorage.removeItem("login");
  }
  getLogin(): string {
    return localStorage.getItem("login");
  }
  isAuthenticated() {
    if (localStorage.getItem("credentials")) {
      return true;
    }
    return false;
  }

  register(user: string, password: string) {
    this.http.post(environment.url + "user/register", {
      password: password,
      username: user
    }
    ).subscribe();
  }

}
