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
    NewsComponent
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
