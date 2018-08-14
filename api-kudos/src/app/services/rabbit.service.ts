import { Injectable } from '@angular/core';

import { Http } from '@angular/http';

// import { conectar } from '../rabbit/send.js';

import 'rxjs/Rx';

@Injectable()
export class RabbitService {

  rabbitURL: 'http://localhost:3000/';

  constructor(private http: Http) { }

  sendMenssage() {
    return this.http.get(this.rabbitURL)
    .map( res => {
      console.log(res.json());
      return res.json();
    });
  }

}

