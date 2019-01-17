import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor(private http: HttpClient) { }

  rootCategoriesMock = [
    {
      id: 1,
      name: "Komputery"
    },
    {
      id: 2,
      name: "Telefony"
    }
  ];
  childrenMock1 = [
    {
      id: 3,
      name: "Laptopy",
    },
    {
      id: 4,
      name: "Stacjonarne"
    }
  ];
  childrenMock2 = [
    {
      id: 5,
      name: "Smartfony",
    },
    {
      id: 6,
      name: "Akcesoria"
    }
  ];

  getRootCategories() {
    return this.http.get(environment.url + 'categories/root').toPromise();
  }
  getChildren(id) {
    return this.http.get<any[]>(environment.url + 'categories/category?parentId=' + id).toPromise();
  }
}
