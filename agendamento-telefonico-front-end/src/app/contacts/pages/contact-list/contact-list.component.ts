import { Component } from '@angular/core';
import { ContactService } from '../../../core/services/contact.service';
import { Contato } from '../../../core/consts/types';
import { DatePipe, NgClass } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-contact-list',
  imports: [DatePipe, FormsModule, RouterLink, NgClass],
  templateUrl: './contact-list.component.html',
  styleUrl: './contact-list.component.css',
})
export class ContactListComponent {
  contatos: Contato[] = [];
  searchQuery: string = '';

  constructor(private contactService: ContactService) {}

  ngOnInit(): void {
    this.loadContatos();
  }

  loadContatos(): void {
    this.contactService.getAll().subscribe((data) => {
      this.contatos = data
        .filter((c) => c.contatoSnAtivo)
        .filter((c) =>
          c.contatoNome.toLowerCase().includes(this.searchQuery.toLowerCase())
        )
        .sort((a, b) => (b.contatoId ?? 0) - (a.contatoId ?? 0));
    });
  }

  inativarContato(id: number | undefined): void {
    this.contactService.inativar(id as number).subscribe(() => {
      this.loadContatos();
    });
  }

  favoritarContato(id: number | undefined): void {
    this.contactService.favoritar(id as number).subscribe(() => {
      this.loadContatos();
    });
  }
}
