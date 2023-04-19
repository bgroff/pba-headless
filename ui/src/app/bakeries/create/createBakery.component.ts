import { Component, OnInit } from '@angular/core';
import { BakeryService as CreateBakeryService } from './createBakery.service';

@Component({
  selector: 'app-root',
  templateUrl: './createBakery.component.html',
  styleUrls: ['./createBakery.component.css']
})
export class CreateBakeryComponent {
  constructor(private bakeryService: CreateBakeryService) { }

  create() {
    this.bakeryService.createBakery(2)
    .subscribe(consentUrl => {
      console.log(consentUrl.consentUrl);
      window.location.href = consentUrl.consentUrl;
    });
  }

}
