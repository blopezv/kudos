import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Kudo } from '../../interfaces/kudo.interface';
import {ActivatedRoute} from '@angular/router';

import { KudosService } from './../../services/kudos.service';

import { RabbitService } from './../../services/rabbit.service';

@Component({
  selector: 'app-kudo-form',
  templateUrl: './kudo-form.component.html',
  styleUrls: ['./kudo-form.component.css']
})
export class KudoFormComponent implements OnInit {

  titulo: string;
  kudoKey; string;

  kudo: Kudo = {
    usuario: '',
    fecha: '',
    persona: '',
    lugar: '',
    tema: '',
    texto: ''
  }

  constructor(private _kudosService: KudosService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.initType();
  }

  initType() {
    this.route.params.subscribe(params => {
      this.kudoKey = params['key'];

      if (this.kudoKey !== 'nuevo') {
          this.titulo = 'Editar ';
          this.getKudo(this.kudoKey);
      } else {
          this.titulo = 'Nuevo ';
      }});
  }

  guardar() {
    console.log(this.kudo);
    if (this.kudoKey !== 'nuevo') {
      this.actualizarKudo(this.kudoKey);
      return;
    }
    this.nuevoKudo();
    console.log('Envio');
    return;
  }

  getKudo(key: string) {
    this._kudosService.getKudo(key)
    .subscribe( res => {
      this.kudo = res;
      console.log(this.kudo);
    },
    error => {
      console.log(error);
    });
  }

  nuevoKudo() {
    this._kudosService.nuevoKudo(this.kudo)
    .subscribe( res => {
      console.log('el kudo se guardo correctamente');
      this.cleanKudo();
    },
    error => {
      console.log(error);
    });
  }

  actualizarKudo(key: string) {
    this._kudosService.actualizarKudo(this.kudo, key)
    .subscribe( res => {
      console.log('el kudo se actualizó correctamente');
    },
    error => {
      console.log(error);
    });
  }

  cleanKudo() {
    this.kudo = {
      usuario: '',
      fecha: '',
      persona: '',
      lugar: '',
      tema: '',
      texto: ''
    }
  }

}
