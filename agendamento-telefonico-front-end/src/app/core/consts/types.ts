export interface ContatoApi {
  contatoId?: number;
  contatoEmail: string;
  contatoNome: string;
  contatoCelular: string;
  contatoTelefone: string;
  contatoSnFavorito: 's' | 'n';
  contatoSnAtivo?: 's' | 'n';
  contatoData?: Date;
}

export interface Contato {
  contatoId?: number;
  contatoEmail: string;
  contatoNome: string;
  contatoCelular: string;
  contatoTelefone: string;
  contatoSnFavorito: boolean;
  contatoSnAtivo?: boolean;
  contatoDhCad?: Date;
}
