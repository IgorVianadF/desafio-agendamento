import { TestBed } from '@angular/core/testing';
import {
  HttpTestingController,
  provideHttpClientTesting,
} from '@angular/common/http/testing';
import { HttpClient, provideHttpClient } from '@angular/common/http';

import { ContactService } from './contact.service';
import { ContatoApi, Contato } from '../consts/types';

describe('ContactService', () => {
  let service: ContactService;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;

  const mockApiUrl = 'http://localhost:8080/api/contacts';
  const mockContatoApi: ContatoApi = {
    contatoId: 1,
    contatoNome: 'Igor Viana',
    contatoEmail: 'igor@gmail.com',
    contatoTelefone: '8182957722',
    contatoCelular: '81982957722',
    contatoSnFavorito: 'n',
    contatoSnAtivo: 's',
  };
  const mockContato: Contato = {
    contatoId: 1,
    contatoNome: 'Igor Viana',
    contatoEmail: 'igor@gmail.com',
    contatoTelefone: '8182957722',
    contatoCelular: '81982957722',
    contatoSnFavorito: false,
    contatoSnAtivo: true,
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [],
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        ContactService,
        { provide: 'environment', useValue: { apiUrl: mockApiUrl } },
      ],
    });

    service = TestBed.inject(ContactService);
    httpMock = TestBed.inject(HttpTestingController);
    httpClient = TestBed.inject(HttpClient);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('fromApi', () => {
    it('Converte os campos de ContatoApi para Contato', () => {
      const result = service['fromApi'](mockContatoApi);
      expect(result).toEqual(mockContato);
    });

    it('Converte no campo contatoSnFavorito e contatoSnAtivo para s/n', () => {
      const apiData: ContatoApi = {
        ...mockContatoApi,
        contatoSnFavorito: 's',
        contatoSnAtivo: 'n',
      };
      const expected: Contato = {
        ...mockContato,
        contatoSnFavorito: true,
        contatoSnAtivo: false,
      };
      const result = service['fromApi'](apiData);
      expect(result).toEqual(expected);
    });
  });

  describe('toApi', () => {
    it('Converte os campos de Contato para ContatoApi', () => {
      const result = service['toApi'](mockContato);
      expect(result).toEqual(mockContatoApi);
    });

    it('Converte o campo contatoSnFavorito e contatoSnAtivo para boolean', () => {
      const contato: Contato = {
        ...mockContato,
        contatoSnFavorito: false,
        contatoSnAtivo: false,
      };
      const expected: ContatoApi = {
        ...mockContatoApi,
        contatoSnFavorito: 'n',
        contatoSnAtivo: 'n',
      };
      const result = service['toApi'](contato);
      expect(result).toEqual(expected);
    });
  });

  describe('getAll', () => {
    it('Realiza requisição GET e retorna um array de contatos', () => {
      const mockResponse: ContatoApi[] = [mockContatoApi];
      service.getAll().subscribe((contacts) => {
        expect(contacts.length).toBe(1);
        expect(contacts[0]).toEqual(mockContato);
      });

      const req = httpMock.expectOne(mockApiUrl);
      expect(req.request.method).toBe('GET');
      expect(req.request.withCredentials).toBeTrue();
      req.flush(mockResponse);
    });
  });

  describe('getById', () => {
    it('Realiza requisição GET e retorna um único contato', () => {
      const id = 1;

      service.getById(id).subscribe((contact) => {
        expect(contact).toEqual(mockContato);
      });

      const req = httpMock.expectOne(`${mockApiUrl}/${id}`);
      expect(req.request.method).toBe('GET');
      expect(req.request.withCredentials).toBeTrue();
      req.flush(mockContatoApi);
    });
  });

  describe('create', () => {
    it('Realiza requisição POST com contato convertido em contatoApi', () => {
      service.create(mockContato).subscribe((contact) => {
        expect(contact).toEqual(mockContato);
      });

      const req = httpMock.expectOne(mockApiUrl);
      expect(req.request.method).toBe('POST');
      expect(req.request.withCredentials).toBeTrue();
      expect(req.request.body).toEqual(mockContatoApi);
      req.flush(mockContatoApi);
    });
  });

  //----------------------------------------------------------------------------------------------
  describe('update', () => {
    it('should send PUT request with converted data', () => {
      const id = 1;

      service.update(id, mockContato).subscribe((contact) => {
        expect(contact).toEqual(mockContato);
      });

      const req = httpMock.expectOne(`${mockApiUrl}/${id}`);
      expect(req.request.method).toBe('PUT');
      expect(req.request.withCredentials).toBeTrue();
      expect(req.request.body).toEqual(mockContatoApi);
      req.flush(mockContatoApi);
    });
  });

  describe('inativar', () => {
    it('Realiza requisição PATCH e inativa contato', () => {
      const id = 1;

      service.inativar(id).subscribe((response) => {
        expect(response).toBeNull();
      });

      const req = httpMock.expectOne(`${mockApiUrl}/${id}/inativar`);
      expect(req.request.method).toBe('PATCH');
      expect(req.request.withCredentials).toBeTrue();
      expect(req.request.body).toEqual({});
      req.flush(null);
    });
  });

  describe('favoritar', () => {
    it('should send PATCH request to favorite endpoint', () => {
      const id = 1;

      service.favoritar(id).subscribe((response) => {
        expect(response).toBeNull();
      });

      const req = httpMock.expectOne(`${mockApiUrl}/${id}/favoritar`);
      expect(req.request.method).toBe('PATCH');
      expect(req.request.withCredentials).toBeTrue();
      expect(req.request.body).toEqual({});
      req.flush(null);
    });
  });
});
