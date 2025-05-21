import { Routes } from '@angular/router';
import { ContactListComponent } from './contacts/pages/contact-list/contact-list.component';
import { ContactFormComponent } from './contacts/pages/contact-form/contact-form.component';

export const routes: Routes = [
  { path: '', component: ContactListComponent },
  { path: 'contato', component: ContactFormComponent },
  { path: 'contato/:id', component: ContactFormComponent },
  { path: '**', redirectTo: '' },
];
