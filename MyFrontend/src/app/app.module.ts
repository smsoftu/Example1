import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MaterialModule } from './material.module';
import { MainServiceService } from './services/main-service.service';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatRippleModule } from '@angular/material/core';
import {InfoDialogComponent} from './components/Dialogs/InfoDialog/InfoDialog.component';
import {CloseMatPopupComponent} from './components/CloseMatPopup/CloseMatPopup.component';
import { MatDialogModule } from '@angular/material/dialog';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UploadFileViewComponent } from './components/UploadFileView/UploadFileView.component';


@NgModule({
  declarations: [AppComponent,InfoDialogComponent,CloseMatPopupComponent,UploadFileViewComponent
 ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    HttpClientModule,
    MatDialogModule,
    AppRoutingModule
  ],
  entryComponents: [
    InfoDialogComponent,
    UploadFileViewComponent


  ],
  exports: [
    MaterialModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule

  ],
  providers: [MainServiceService],
  bootstrap: [AppComponent]
})
export class AppModule {

  

 }

 platformBrowserDynamic()
  .bootstrapModule(AppModule)
  .catch((err) => console.error(err));