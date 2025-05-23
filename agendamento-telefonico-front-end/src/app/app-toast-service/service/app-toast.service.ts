import { Injectable } from '@angular/core';
export interface ToastInfo {
  header: string;
  body: string;
  delay?: number;
  class?: string;
}

@Injectable({ providedIn: 'root' })
export class AppToastService {
  toasts: ToastInfo[] = [];

  show(header: string, body: string) {
    this.toasts.push({ header, body });
  }

  remove(toast: ToastInfo) {
    this.toasts = this.toasts.filter((t) => t != toast);
  }
}
