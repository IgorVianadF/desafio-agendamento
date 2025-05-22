import { Routes } from '@angular/router';
import { ContactListComponent } from './contacts/pages/contact-list/contact-list.component';
import { ContactFormComponent } from './contacts/pages/contact-form/contact-form.component';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { path: '', component: ContactListComponent },
  {
    path: 'contato',
    component: ContactFormComponent,
    canActivate: [authGuard],
  },
  {
    path: 'contato/:id',
    component: ContactFormComponent,
    canActivate: [authGuard],
  },
  { path: '**', redirectTo: '' },
];
