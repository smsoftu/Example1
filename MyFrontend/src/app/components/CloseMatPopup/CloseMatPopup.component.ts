import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'close-mat-popup',
  templateUrl: './CloseMatPopup.component.html',
  styleUrls: ['./CloseMatPopup.component.css']
})
export class CloseMatPopupComponent implements OnInit {

  @Output()
  close = new EventEmitter<void>();

  constructor() { }

  ngOnInit() {
  }

}
