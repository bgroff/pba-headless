import { Component, OnInit } from '@angular/core';
import { Bakery } from './bakery';
import { BakeryService } from './bakery.service';

@Component({
  selector: 'app-root',
  templateUrl: './bakery.component.html',
  styleUrls: ['./bakery.component.css']
})
export class BakeryComponent implements OnInit {
  title = 'Bakeries';
  bakeries: Bakery[] = [];

  constructor(private bakeryService: BakeryService) { }

  ngOnInit(): void {
    this.get();
  }

  get() {
    this.bakeryService.getBakeries()
    .subscribe(bakeries => this.bakeries = bakeries);
  }

}
