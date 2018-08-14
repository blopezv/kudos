import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppRoutingModule } from './app.routing';

import { AppComponent } from './app.component';

import { KudoFormComponent } from './components/kudo-form/kudo-form.component';
import { KudosListComponent } from './components/kudos-list/kudos-list.component';

import { KudosService } from './services/kudos.service';
import { KeysPipe } from './pipes/keys.pipe';
import { RabbitService } from './services/rabbit.service';


@NgModule({
  declarations: [
    AppComponent,
    KudoFormComponent,
    KudosListComponent,
    KeysPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [KudosService, RabbitService],
  bootstrap: [AppComponent]
})
export class AppModule { }
