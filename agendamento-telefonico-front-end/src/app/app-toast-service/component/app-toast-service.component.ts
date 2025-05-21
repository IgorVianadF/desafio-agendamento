import { Component } from '@angular/core';
import { AppToastService } from '../service/app-toast.service';
import { NgbToast } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-toast-service',
  imports: [NgbToast],
  templateUrl: './app-toast-service.component.html',
  styleUrl: './app-toast-service.component.css',
})
export class AppToastServiceComponent {
  constructor(public toastService: AppToastService) {}

  class: string = '';
}
