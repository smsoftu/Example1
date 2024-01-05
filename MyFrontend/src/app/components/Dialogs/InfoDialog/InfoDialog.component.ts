import {Component, Inject, Input, OnInit, OnDestroy} from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import {DomSanitizer, SafeHtml} from '@angular/platform-browser';


@Component({
  selector: 'app-simple-dialog',
  providers: [],
  templateUrl: './InfoDialog.component.html',
  styleUrls: ['./InfoDialog.component.css']
})
export class InfoDialogComponent implements OnInit {

  description: string;
  text = '';
  content: SafeHtml;
  data:any;

  constructor(private dialogRef: MatDialogRef<InfoDialogComponent>,
              private sanitizer: DomSanitizer,
              @Inject(MAT_DIALOG_DATA) {description, text, customData}
) {
    this.data=customData;
    this.description = description;
    if (text) {
      this.text += `${text}<br/>`;
    }
    if (Array.isArray(customData)) {
      customData.forEach(message => {
        this.text += `${message}<br/>`;
      });
    }
    this.content = sanitizer.bypassSecurityTrustHtml(this.text);
    dialogRef.disableClose = true;

    
  }

  ngOnInit() {
  }

  onCloseClick() {
   this.dialogRef.close(this.data);
    
    window.dispatchEvent(new Event('resize'));
  }

   
}
