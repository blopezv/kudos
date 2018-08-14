import { Injectable } from '@angular/core';

import { Http, Headers } from '@angular/http';
import { Kudo } from '../interfaces/kudo.interface';
import 'rxjs/Rx';

@Injectable()
export class KudosService {

  fireKudosURL = 'https://kudos-d5faa.firebaseio.com/kudos.json';
  fireKudoURL = 'https://kudos-d5faa.firebaseio.com/kudos/';

  constructor(private http: Http) { }

  getKudos() {
    return this.http.get(this.fireKudosURL)
    .map( res => {
      console.log(res.json());
      return res.json();
    });
  }
  getKudo(key$: string) {
    const url = ` ${ this.fireKudoURL }/${ key$ }.json `;
    return this.http.get(url)
    .map( res => {
      console.log(res.json());
      return res.json();
    });
  }
  nuevoKudo(kudo: Kudo) {
    // const body = JSON.stringify(kudo);
    const headers = new Headers({
      'Content-Type': 'application/json'
    });
    return this.http.post(this.fireKudosURL, kudo, { headers } )
    .map( res => {
      console.log(res.json());
      return res.json();
    });
  }

  actualizarKudo(kudo: Kudo, key$: string) {
    const headers = new Headers({
      'Content-Type': 'application/json'
    });
    const url = ` ${ this.fireKudoURL }/${ key$ }.json `;
    return this.http.put(url, kudo, { headers } )
    .map( res => {
      console.log(res.json());
      return res.json();
    });
  }
  eliminarKudo(key$: string) {
    const headers = new Headers({
      'Content-Type': 'application/json'
    });
    const url = ` ${ this.fireKudoURL }/${ key$ }.json `;
    return this.http.delete(url, { headers } )
    .map( res => {
      console.log(res.json());
      return res.json();
    });
  }

  // ---- rabbit

}
