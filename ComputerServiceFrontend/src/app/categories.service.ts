import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor() { }

  getRootCategories() {
    return [
      {
        id:1,
        name: "Komputery"
      },
      {
        id:2,
        name: "Telefony"
      }
    ]
  }
  getChildren(id) {
    if(id==1)
    return [
      {
        id:3,
        name: "Laptopy",
      },
      {
        id:4,
        name:"Stacjonarne"
      }
    ]
    if(id==2)
    return[
      {
        id:5,
        name:"Smartfony",
      },
      {
        id:6,
        name:"Akcesoria"
      }
    ]
    else    {
      return [];
    }
  }
}
