import { NgClass } from '@angular/common';
import { Component, signal } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ContactService } from '../../../core/services/contact.service';
import { Contato } from '../../../core/consts/types';
import { AppToastService } from '../../../app-toast-service/service/app-toast.service';

@Component({
  selector: 'app-contact-form',
  imports: [RouterModule, ReactiveFormsModule, NgClass],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css',
})
export class ContactFormComponent {
  contatoId = signal<number | null>(null);
  private edit = signal(false);
  small = screen.width < 768;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private contactService: ContactService,
    private toastService: AppToastService
  ) {}

  ContatoForm: FormGroup = new FormGroup({
    contatoNome: new FormControl('', [Validators.required]),
    contatoEmail: new FormControl('', [Validators.required, Validators.email]),
    contatoTelefone: new FormControl('', [
      Validators.required,
      Validators.maxLength(10),
      Validators.minLength(10),
    ]),
    contatoCelular: new FormControl('', [
      Validators.required,
      Validators.maxLength(11),
      Validators.minLength(11),
    ]),
    contatoSnAtivo: new FormControl('s'),
  });

  ngOnInit() {
    this.contatoId.set(this.route.snapshot.params['id']);
    if (this.contatoId()) {
      this.edit.set(true);
      this.getValues();
    }
  }

  private getValues() {
    this.contactService
      .getById(this.contatoId() as number)
      .subscribe((res) => this.ContatoForm.patchValue(res));
  }
  saveContact(): void {
    if (this.ContatoForm.invalid) {
      this.toastService.show('Algo errado', 'Preencha todos os campos');
      return;
    }
    const newContact: Contato = {
      ...this.ContatoForm.value,
    };
    if (this.edit()) {
      this.contactService
        .update(this.contatoId() as number, newContact)
        .subscribe({
          error: (error) => {
            if (error.status === 409) {
              this.toastService.show(
                'Algo errado',
                'Número já existe na sua agenda'
              );
            } else {
              this.toastService.show(
                'Algo errado',
                'Algum erro ocorreu no servidor!'
              );
            }
            console.error('Error updating contact:', error);
          },
          complete: () => {
            this.toastService.show('Sucesso', 'Contato editado com sucesso');
            this.router.navigate(['']);
          },
        });
      return;
    }

    this.contactService.create(newContact).subscribe({
      next: () => {},
      error: (error) => {
        console.error('Error creating contact:', error);

        if (error.status === 409) {
          this.toastService.show(
            'Algo errado',
            'Número já existe na sua agenda'
          );
        }
      },
      complete: () => {
        this.toastService.show('Sucesso', 'Contato criado com sucesso');
        this.router.navigate(['']);
      },
    });
  }

  getValidationMessage(controlName: string): string {
    const control = this.ContatoForm.get(controlName);

    if (!control || !control.errors || (!control.touched && !control.dirty))
      return '';

    if (!control || !control.errors || !control.touched) return '';

    if (control.errors['required']) {
      return 'Campo obrigatório';
    }

    if (control.errors['email']) {
      return 'Formato de e-mail inválido';
    }

    if (control.errors['minlength']) {
      return `Mínimo ${control.errors['minlength'].requiredLength} caracteres`;
    }

    if (control.errors['maxlength']) {
      return `Máximo ${control.errors['maxlength'].requiredLength} caracteres`;
    }

    if (control.errors['pattern']) {
      return 'Formato inválido';
    }

    return 'Valor inválido';
  }
}
