export interface ContatoApi {
  contatoId?: number;
  contatoEmail: string;
  contatoNome: string;
  contatoCelular: string;
  contatoTelefone: string;
  contatoData?: Date;
  contatoSnFavorito?: 's' | 'n';
  contatoSnAtivo?: 's' | 'n';
}

export interface Contato {
  contatoId?: number;
  contatoEmail: string;
  contatoNome: string;
  contatoCelular: string;
  contatoTelefone: string;
  contatoSnFavorito?: boolean;
  contatoSnAtivo?: boolean;
  contatoDhCad?: Date;
}
