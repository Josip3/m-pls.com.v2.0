import {HomeComponent} from "./main/home/home.component";
import {ChatComponent} from "./cabinet/call-center/chat/chat.component";
import {CabinetComponent} from "./cabinet/cabinet.component";
import {MainComponent} from "./main/main.component";
import {Routes} from "@angular/router";
import {CallCenterComponent} from "./cabinet/call-center/call-center.component";
import {AboutUsComponent} from "./main/about-us/about-us.component";

export const mainRoutes: Routes = [
    {
        path: '', component: MainComponent, children: [
            {
                path: '', component: HomeComponent
            },
            {
                path: 'about-us', component: AboutUsComponent
            }
        ]
    },
    {path: 'cabinet', component: CabinetComponent, children:[
            {path: 'call-center',component:CallCenterComponent,children:[
                    {path: 'chat', component:ChatComponent}
                ]},
        ]},
];