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
  isScreenSmall() {
    return window.innerWidth < 768;
  }

  contatos: Contato[] = [];
  searchQuery: string = '';

  constructor(private contactService: ContactService) {}

  ngOnInit(): void {
    this.loadContatos();
  }

  loadContatos(): void {
    this.contactService.getAll().subscribe((data) => {
      this.contatos = this.filterData(data);
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

  deletarContato(id: number | undefined): void {
    this.contactService.delete(id as number).subscribe(() => {
      this.loadContatos();
    });
  }

  filterData(data: Contato[]): Contato[] {
    return data
      .filter((c) =>
        c.contatoNome
          .toLowerCase()
          .normalize('NFD')
          .replace(/[\u0300-\u036f]/g, '')
          .includes(
            this.searchQuery
              .toLowerCase()
              .normalize('NFD')
              .replace(/[\u0300-\u036f]/g, '')
          )
      )
      .sort((a, b) => {
        if (Number(b.contatoSnAtivo) !== Number(a.contatoSnAtivo)) {
          return Number(b.contatoSnAtivo) - Number(a.contatoSnAtivo);
        }
        if (Number(b.contatoSnFavorito) !== Number(a.contatoSnFavorito)) {
          return Number(b.contatoSnFavorito) - Number(a.contatoSnFavorito);
        }
        return (a.contatoId ?? 0) - (b.contatoId ?? 0);
      });
  }
}
