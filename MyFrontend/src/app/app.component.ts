import { Component, OnInit } from '@angular/core';
import { MainServiceService } from './services/main-service.service';
import { InfoDialogComponent } from './components/Dialogs/InfoDialog/InfoDialog.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import * as bootstrap from 'bootstrap';
import { UploadItem } from './dto/upload-item';
import { UploadFileViewComponent } from './components/UploadFileView/UploadFileView.component';
import { UploadDto } from './dto/dialog/upload-dto';
import { environment } from '../environments/environment.debug';


@Component({
  selector: 'app-root',
  providers: [MainServiceService],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'myWeb';
  logged:boolean;
  login:string;
  password:string;
  limit:number;
  role:string;
  items:UploadItem[]=[];
  userId:number;
  sessionId:string;
  outpuFileName:string;

  get filesUploaded():boolean {
    if (this.items&&this.items.length>0) {
    for(let i=0;i<this.items.length;i++) {
      if (this.items[i].fileName) return true;
    }
    return false;
  } else {
    return false;
  }
  }

  ngOnInit(): void {
    
    this.logged=false;


  }



  constructor(private mainService:MainServiceService,private simpleDialog:MatDialog,private http:HttpClient) {

  }





  info(text:string,warnText:string) {
    this.simpleDialog.open(InfoDialogComponent, {
      disableClose: true,

      data: {description: warnText, text: text}
    });
    return;
  }

  makeLogin() {
    this.mainService.login(this.login,this.password).subscribe( data => {
      if (data.status=='OK') {
        this.logged=true;
        this.limit=data.limit;
        this.info('Vaše přihlášení bylo úspěšné','Info');
        this.role=data.role;
        this.userId=data.userId;
        this.sessionId=data.sessionId;
        this.items=[];
        for(let i=0;i<this.limit;i++) {
          var item=new UploadItem();
          item.item=i+1;
          item.fileName='';
          this.items.push(item);
        }
      } else {
        this.logged=false;
        this.info('Nesprávné přihlašovací informace','Error');
      }
    },(err)=> {
      this.info(err.message,'Error');
    })

  }

  makeLogout() {
    this.mainService.logout(this.sessionId).subscribe ( data => {
      this.logged=false;
      this.login='';
      this.password='';
      this.role='';
      this.sessionId='';
      this.items=[];
  
    },(err)=> {
      this.info(err.message,'Error');
    });
  }

  changeLimit() {
    this.mainService.setLimit(this.login,this.password,this.limit,this.sessionId).subscribe( data => {
      if (data.status=='OK') {
        this.info('Limit byl úspěšně změněn','Info');
        this.limit=data.limit;
        this.items=[];
        for(let i=0;i<this.limit;i++) {
          var item=new UploadItem();
          item.item=i+1;
          item.fileName='';
          this.items.push(item);
        }

      } else {
        this.info('Změna limitu nebyla provedena','Error');
        
      }
    },(err)=> {
      this.info(err.message,'Error');
    });
  }

  callUpload(item:UploadItem) {
    const dialogRef = this.simpleDialog.open(UploadFileViewComponent, {
      disableClose: true,

      data: new UploadDto(this.limit,item.item,this.sessionId)
    });

    dialogRef.afterClosed().subscribe( data => {
      if (data) {
        console.log(data);
        this.logged=false;
        for(let i=0;i<this.items.length;i++) {
          if (data.counter==this.items[i].item) {
            this.items[i].fileName=data.filename;
            break;
          }
        }
        this.logged=true;
        }
      } 
      );

  }

  download() {
    this.mainService.printReport(environment.backendUrl + '/download?sessionId='+this.sessionId+'&outputFileName='+this.outpuFileName);
      this.items=[];
      for(let i=0;i<this.limit;i++) {
        var item=new UploadItem();
        item.item=i+1;
        item.fileName='';
        this.items.push(item);
      }      
  }

}

