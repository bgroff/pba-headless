import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BakeryComponent } from './bakeries/bakery.component';
import { CreateBakeryComponent } from './bakeries/create/createBakery.component';

const routes: Routes = [
  {path: '', redirectTo: '/bakeries', pathMatch: 'full'},
  {path: 'bakeries', component: BakeryComponent},
  {path: 'bakeries/createConnection', component: CreateBakeryComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
