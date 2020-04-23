import {Component, NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import {EditorComponent} from './editor/editor.component';
import {CodesComponent} from './codes/codes.component';
import { ProgettiComponent } from './progetti/progetti.component';
import {SupportComponent} from './support/support.component';

/**
 * Modulo di routing dell'admin. Qui ci sono i percorsi che un admin può seguire:
 * appena fa il login viene caricato nel <router-outlet> di app-component il layout e nel
 * <router-outlet> del layout (come percorsi "children") vengono visualizzati gli altri
 * (qui sotto sono indentati).
 *
 * @author Vittorio Valent
 *
 * @see AdminLayoutComponent
 *
 * @see layout
 */
const routes: Routes = [
  { path: 'admin-dashboard', component: AdminLayoutComponent, children:[
    { path: '', component: AdminDashboardComponent},
    { path: 'users', component: UsersComponent},
    { path: 'work-in-progress', component: WorkInProgressComponent},
    { path: 'editor', component: EditorComponent},
    { path: 'codes', component: CodesComponent},
    { path: 'progetti', component: ProgettiComponent},
    { path: 'support', component: SupportComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
