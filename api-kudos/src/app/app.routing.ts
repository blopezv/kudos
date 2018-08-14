import { RouterModule, Routes } from '@angular/router';

import { KudoFormComponent } from './components/kudo-form/kudo-form.component';
import { KudosListComponent } from './components/kudos-list/kudos-list.component';
import { NgModule } from '../../node_modules/@angular/core';

const routes: Routes = [
  {
    path: 'kudos',
    component: KudosListComponent
  },
  {
    path: 'kudo',
    component: KudoFormComponent
  },
  {
    path: 'kudo/:key',
    component: KudoFormComponent
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: 'kudos'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
