import { Component, OnInit } from '@angular/core';
import { Kudo } from '../../interfaces/kudo.interface';

import { KudosService } from './../../services/kudos.service';

@Component({
  selector: 'app-kudos-list',
  templateUrl: './kudos-list.component.html',
  styleUrls: ['./kudos-list.component.css']
})
export class KudosListComponent implements OnInit {

  kudos: any[] = [] ;
  // kudo: Kudo;

  constructor( private _kudosService: KudosService) {
  }

  ngOnInit() {
    this.getKudos();
  }

  getKudos() {
    this._kudosService.getKudos()
    .subscribe( res => {
      this.kudos = res;
      console.log(this.kudos);
    },
    error => {
      console.log(error);
    });
  }

  eliminarKudo(key: string) {
    this._kudosService.eliminarKudo(key)
    .subscribe( res => {
      console.log('el kudo se eliminó correctamente');
      this.getKudos();
    },
    error => {
      console.log(error);
    });
  }

}
