import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule, Routes} from "@angular/router";
import {MainComponent} from './main/main.component';
import {HeaderComponent} from './main/header/header.component';
import {FooterComponent} from './main/footer/footer.component';
import {HomeComponent} from './main/home/home.component';
import { GalleryContainerComponent } from './main/home/gallery-container/gallery-container.component';
import { ChatHelperComponent } from './sourse/chat-helper/chat-helper.component';

const routes: Routes = [
  {
    path: '', component: MainComponent, children: [
    {
      path: '', component: HomeComponent,
    },

  ]
  },
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
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
