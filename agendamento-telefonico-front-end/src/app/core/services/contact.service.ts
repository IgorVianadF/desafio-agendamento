import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Contato, ContatoApi } from '../consts/types';

@Injectable({
  providedIn: 'root',
})
export class ContactService {
  constructor(private http: HttpClient) {}
  private apiUrl = environment.apiUrl;

  private fromApi(apiData: ContatoApi): Contato {
    return {
      ...apiData,
      contatoSnFavorito: apiData?.contatoSnFavorito === 's',
      contatoSnAtivo: apiData?.contatoSnAtivo === 's',
    };
  }

  private toApi(contato: Contato): ContatoApi {
    return {
      ...contato,
      contatoSnFavorito: contato?.contatoSnFavorito ? 's' : 'n',
      contatoSnAtivo: contato?.contatoSnAtivo ? 's' : 'n',
    };
  }

  getAll(): Observable<Contato[]> {
    return this.http
      .get<ContatoApi[]>(this.apiUrl, { withCredentials: true })
      .pipe(map((apiData) => apiData.map(this.fromApi)));
  }

  getById(id: number): Observable<Contato> {
    return this.http
      .get<ContatoApi>(`${this.apiUrl}/${id}`, { withCredentials: true })
      .pipe(map(this.fromApi));
  }

  create(contato: Contato): Observable<Contato> {
    const body = this.toApi(contato);
    return this.http
      .post<ContatoApi>(this.apiUrl, body, { withCredentials: true })
      .pipe(map(this.fromApi));
  }

  update(id: number, contato: Contato): Observable<Contato> {
    const body = this.toApi(contato);
    return this.http
      .put<ContatoApi>(`${this.apiUrl}/${id}`, body, { withCredentials: true })
      .pipe(map(this.fromApi));
  }

  inativar(id: number): Observable<void> {
    return this.http.patch<void>(
      `${this.apiUrl}/${id}/inativar`,
      {},
      { withCredentials: true }
    );
  }

  favoritar(id: number): Observable<void> {
    return this.http.patch<void>(
      `${this.apiUrl}/${id}/favoritar`,
      {},
      { withCredentials: true }
    );
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, {
      withCredentials: true,
    });
  }
}
