import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {Router, RouterModule, Routes} from "@angular/router";
import {MainComponent} from './main/main.component';
import {HeaderComponent} from './main/header/header.component';
import {FooterComponent} from './main/footer/footer.component';
import {HomeComponent} from './main/home/home.component';
import {GalleryContainerComponent} from './main/home/gallery-container/gallery-container.component';
import {ChatHelperComponent} from './sourse/chat-helper/chat-helper.component';
import {LoginComponent} from "./login/login.component";
import {Http, HttpModule, RequestOptions, XHRBackend} from "@angular/http";
import {HttpClient} from "../environments/service/http-client";
import {CookieService} from "angular2-cookie/core";
import {NewsComponent} from './main/news/news.component';
import {AgmCoreModule} from "@agm/core";
import {AgmSnazzyInfoWindowModule} from "@agm/snazzy-info-window";
import { CabinetComponent } from './cabinet/cabinet.component';
import { CallCenterComponent } from './cabinet/call-center/call-center.component';
import { ChatComponent } from './cabinet/call-center/chat/chat.component';
import { ChatRoomComponent } from './cabinet/call-center/chat/chat-room/chat-room.component';

const routes: Routes = [
  {
    path: '', component: MainComponent, children: [
    {
      path: '', component: HomeComponent,

    },
    {path: 'news', component: NewsComponent}
  ]
  },
  // {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'cabinet', component: CabinetComponent, children:[
    {path: 'call-center',component:CallCenterComponent,children:[
      {path: 'chat', component:ChatComponent,children:[
        {path:'chat-room/:id',component:ChatRoomComponent}
      ]},
    ]},
  ]},
];

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    GalleryContainerComponent,
    ChatHelperComponent,
    LoginComponent,
    NewsComponent,
    CabinetComponent,
    CallCenterComponent,
    ChatComponent,
    ChatRoomComponent,
  ],
  imports: [
    HttpModule,
    BrowserModule,
    RouterModule.forRoot(routes, {useHash: true}),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDio2qZDpD9ifaAlAqsk-1Gr8zi0fblqY8'
    }),
    AgmSnazzyInfoWindowModule
  ],
  providers: [CookieService, {
    provide: Http,
    useFactory: factory,
    deps: [XHRBackend, RequestOptions, Router]
  },],
  bootstrap: [AppComponent]
})
export class AppModule {
}
export function factory(backend: XHRBackend, defaultOptions: RequestOptions, router: Router) {
  return new HttpClient(backend, defaultOptions, router);
}
