import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { UploadDto } from '../../dto/dialog/upload-dto';
import { MainServiceService } from '../../services/main-service.service';
import { UploadFileInformation } from '../../dto/upload-file-information';
import { environment } from '../../../environments/environment.debug';
import { HttpClient } from '@angular/common/http';
import { InfoDialogComponent } from '../Dialogs/InfoDialog/InfoDialog.component';

@Component({
  selector: 'app-upload-file-view',
  templateUrl: './UploadFileView.component.html',
  styleUrls: ['./UploadFileView.component.css']
})
export class UploadFileViewComponent implements OnInit {

  limit:number;
  counter:number;
  sessionId:string;
  uploadDetail:UploadFileInformation;
  


  constructor(public dialogRef: MatDialogRef<UploadFileViewComponent>,
    private mainService: MainServiceService,
    private simpleDialog: MatDialog,
    private http:HttpClient, 
    @Inject(MAT_DIALOG_DATA) public data: UploadDto
    ) { }

  ngOnInit(): void {
    this.limit=this.data.limit;
    this.counter=this.data.counter;
    this.sessionId=this.data.sessionId;
    this.uploadDetail=new UploadFileInformation();
    this.uploadDetail.sessionId=this.sessionId;
    this.uploadDetail.counter=this.counter;
    
  }

  info(text:string,warnText:string) {
    this.simpleDialog.open(InfoDialogComponent, {
      disableClose: true,

      data: {description: warnText, text: text}
    });
    return;
  }


  uploadFile(event) {

    console.log(event);
    let reader = new FileReader();
    var curFiles=event.target.files;
    let file = curFiles[0];
    reader.readAsArrayBuffer(file);
      reader.onload = () => {
        this.uploadDetail.uploadFile=reader.result;
        this.uploadDetail.filename=file.name;
        this.uploadDetail.type=file.type;
      };

  }

  makeTransfer() {
     this.mainService.makeUploadAttachment(this.uploadDetail).subscribe ( data => {
        if (data.message!='OK') {
          this.info(data.message,'Error');
        } else {
        this.dialogRef.close(this.uploadDetail);
        }
     },(error)=>{
      this.info(error.message,'Error');
     });
  }
  
    onCloseClick() {
      this.dialogRef.close();
  
    }

    onSubmit() {
      var i=0;
    }
  
}
