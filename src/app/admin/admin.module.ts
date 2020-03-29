import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import {EditorComponent} from './editor/editor.component';
import { AceEditorModule } from 'ng2-ace-editor';
import {CodesComponent} from './codes/codes.component';
import { ProgettiComponent } from './progetti/progetti.component';
import {SupportComponent} from './support/support.component';

import {PERFECT_SCROLLBAR_CONFIG, PerfectScrollbarConfigInterface, PerfectScrollbarModule} from 'ngx-perfect-scrollbar';
import {DemoMaterialModule} from "../material.module";

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};
/**
 * Modulo dell'admin, qui vengono dichiarate le component che utilizza
 * l'admin. Questo modulo importa AdminRoutingModule.
 *
 * @author Vittorio Valent
 *
 * @see AdminRoutingModule
 */
@NgModule({
  declarations: [AdminDashboardComponent, UsersComponent, WorkInProgressComponent, EditorComponent, CodesComponent,ProgettiComponent,SupportComponent],
    imports: [
        CommonModule,
        AdminRoutingModule,
        FormsModule,
        AceEditorModule,
        PerfectScrollbarModule,
        DemoMaterialModule
    ],
  providers: [
    {
      provide: PERFECT_SCROLLBAR_CONFIG,
      useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
    }
  ]
})
export class AdminModule { }
