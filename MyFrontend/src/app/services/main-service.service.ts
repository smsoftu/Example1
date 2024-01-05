import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.debug';
import { UploadFileInformation } from '../dto/upload-file-information';

@Injectable({
  providedIn: 'root'
})
export class MainServiceService {

  constructor(private http: HttpClient) {
  }

  login(user:string,password:string) {
    return this.http.post<any>(environment.backendUrl + '/login', {
      login:user,
      password: password
    });

  }

  setLimit(user:string,password:string,limit:number,sessionId:string) {
    return this.http.post<any>(environment.backendUrl + '/setLimit', {
      login:user,
      password: password,
      limit: limit,
      sessionId: sessionId
    });

  }

  
  makeUploadAttachment(item: UploadFileInformation) {
    let uri=environment.backendUrl + '/upload-file';
    const data : FormData = new FormData();
    let blob=new Blob([item.uploadFile], {type: item.type});
    
    var sessionId:string=item.sessionId;
    var counter:string=Number(item.counter).toString();
    data.append("sessionId",sessionId);
    data.append("counter",counter);
    data.append("fileName",item.filename);
    data.append("file",blob,item.filename);
    

    return this.http.post<any>(uri, data/*,{ headers: header}*/);

  }

  logout(sessionId:string) {
    return this.http.post<any>(environment.backendUrl + '/logout', {
      sessionId:sessionId
    });

  }

  printReport(redirect: string) {
    const link = document.createElement('a');
    link.target = '_blank';
  link.href = redirect;
  link.setAttribute('visibility', 'hidden');
  link.click();
  }


}
