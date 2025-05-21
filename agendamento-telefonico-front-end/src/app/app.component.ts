import { Component } from '@angular/core';
import { ContactListComponent } from './contacts/pages/contact-list/contact-list.component';
import { NavbarComponent } from './layout/component/navbar/navbar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppToastServiceComponent } from './app-toast-service/component/app-toast-service.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [NavbarComponent, RouterModule, NgbModule, AppToastServiceComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'agendamento-telefonico-front-end';
}
