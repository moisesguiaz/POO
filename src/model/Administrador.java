package model;

import java.time.LocalDate;
import java.util.List;

public class Administrador extends Usuario {
    private String cargo;

    public Administrador(int id, String nome, String login, String senha, String cpf, String cargo) {
        super(id, nome, login, senha, cpf);
        this.cargo = cargo;
    }

    // --- MÉTODOS DE CRIAÇÃO ---

    public void criarObra(List<Obra> catalogoObras, String titulo, String artista, 
                          int ano, String estilo, double valor, String conservacao) {
        int novoId = catalogoObras.size() + 1;
        Obra novaObra = new Obra(novoId, titulo, artista, ano, estilo, valor, "Acervo", conservacao);
        catalogoObras.add(novaObra);
        System.out.println("Sucesso: Obra '" + titulo + "' cadastrada no acervo.");
    }

    public void criarExposicao(List<Exposicao> listaExposicoes, String titulo, LocalDate inicio, 
                               LocalDate fim, int capacidade, String local) {
        int novoId = listaExposicoes.size() + 1;
        Exposicao novaExp = new Exposicao(novoId, titulo, inicio, fim, capacidade, local);
        listaExposicoes.add(novaExp);
        System.out.println("Sucesso: Exposição '" + titulo + "' criada.");
    }

    public void incluirObraEmExposicao(Exposicao exposicao, Obra obra) {
        if(exposicao.adicionarObra(obra)) {
            System.out.println("Obra '" + obra.getTitulo() + "' adicionada à exposição '" + exposicao.getTitulo() + "'.");
        }
    }

    // --- MÉTODOS DE RESTAURAÇÃO ---

    public void iniciarRestauracao(List<Restauracao> restauracoes, List<Exposicao> exposicoes, 
                                   Obra obra, LocalDate dataInicio) {
        if (obra.isEmRestauracao()) {
            System.out.println("Erro: A obra já está em processo de restauração.");
            return;
        }

        // Regra: Remover da exposição se entrar em restauração
        if (obra.isEmExposicao()) {
            System.out.println("Aviso: A obra estava em exposição. Removendo-a automaticamente...");
            for (Exposicao exp : exposicoes) {
                if (exp.getObras().contains(obra)) {
                    exp.removerObra(obra);
                }
            }
        }

        int novoId = restauracoes.size() + 1;
        Restauracao novaRestauracao = new Restauracao(novoId, obra, dataInicio);
        restauracoes.add(novaRestauracao);
        
        System.out.println("Restauração iniciada para a obra: " + obra.getTitulo());
    }


    // --- MÉTODOS DE EDIÇÃO E EXCLUSÃO ---

    public void excluirObra(List<Obra> catalogoObras, int idObra) {
        Obra obraParaRemover = null;
        for (Obra o : catalogoObras) {
            if (o.getId() == idObra) {
                obraParaRemover = o;
                break;
            }
        }
    
        if (obraParaRemover == null) {
            System.out.println("Erro: Obra não encontrada.");
            return;
        }
    
        if (obraParaRemover.isEmExposicao()) {
            System.out.println("Erro: Obra em exposição ativa.");
            return;
        }
        
        if (obraParaRemover.isEmRestauracao()) {
            System.out.println("Erro: Obra em restauração.");
            return;
        }
    
        catalogoObras.remove(obraParaRemover);
        System.out.println("Sucesso: Obra removida.");
    }

    public void editarObra(List<Obra> catalogoObras, int idObra, String novoTitulo, 
                           String novoArtista, int novoAno, String novaConservacao) {
        Obra obra = null;
        for (Obra o : catalogoObras) {
            if (o.getId() == idObra) {
                obra = o;
                break;
            }
        }

        if (obra != null) {
            obra.setTitulo(novoTitulo);
            obra.setArtista(novoArtista);
            obra.setAno(novoAno);
            obra.setEstadoConservacao(novaConservacao);
            System.out.println("Sucesso: Dados da obra atualizados.");
        } else {
            System.out.println("Erro: Obra não encontrada.");
        }
    }


    // 1. Finalizar Restauração (Corrige o erro do App.java)
    public void finalizarRestauracao(List<Restauracao> restauracoes, int idObra, LocalDate dataFim) {
        Restauracao alvo = null;
        for (Restauracao r : restauracoes) {
            if (r.getObra().getId() == idObra && r.getStatus().equals("Em andamento")) {
                alvo = r;
                break;
            }
        }
        if (alvo != null) {
            alvo.concluirRestauracao(dataFim);
        } else {
            System.out.println("Erro: Nenhuma restauração ativa para esta obra.");
        }
    }

    // 2. Remover Obra da Exposição (Sem ser por restauração)
    public void removerObraDaExposicao(Exposicao exposicao, Obra obra) {
        if (exposicao.getObras().contains(obra)) {
            exposicao.removerObra(obra);
            System.out.println("Sucesso: Obra removida da exposição.");
        } else {
            System.out.println("Erro: Obra não está nesta exposição.");
        }
    }

    // 3. Excluir Exposição
    public void excluirExposicao(List<Exposicao> exposicoes, int idExpo) {
        Exposicao alvo = null;
        for (Exposicao e : exposicoes) {
            if (e.getId() == idExpo) { alvo = e; break; }
        }
        if (alvo != null) {
            // Libera as obras antes de excluir
            List<Obra> obrasNaExpo = List.copyOf(alvo.getObras());
            for (Obra o : obrasNaExpo) alvo.removerObra(o);
            
            exposicoes.remove(alvo);
            System.out.println("Sucesso: Exposição excluída.");
        } else {
            System.out.println("Erro: Exposição não encontrada.");
        }
    }

    // 4. Editar Exposição
    public void editarExposicao(List<Exposicao> exposicoes, int idExpo, String novoTitulo, int novaCap) {
        Exposicao alvo = null;
        for (Exposicao e : exposicoes) {
            if (e.getId() == idExpo) { alvo = e; break; }
        }
        if (alvo != null) {
            alvo.setTitulo(novoTitulo);
            if (novaCap > 0) alvo.setCapacidade(novaCap);
            System.out.println("Sucesso: Exposição editada.");
        } else {
            System.out.println("Erro: Exposição não encontrada.");
        }
    }

    // --- GESTÃO DE VISITANTES ---

    public void listarVisitantes(List<Usuario> usuarios) {
        System.out.println("--- Lista de Visitantes ---");
        boolean achou = false;
        for (Usuario u : usuarios) {
            if (u instanceof Visitante) {
                System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Login: " + u.getLogin());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum visitante cadastrado.");
    }

    public void excluirVisitante(List<Usuario> usuarios, int idVisitante) {
        Usuario alvo = null;
        for (Usuario u : usuarios) {
            if (u.getId() == idVisitante && u instanceof Visitante) {
                alvo = u;
                break;
            }
        }
        
        if (alvo != null) {
            usuarios.remove(alvo);
            System.out.println("Sucesso: Visitante '" + alvo.getNome() + "' excluído.");
        } else {
            System.out.println("Erro: Visitante não encontrado.");
        }
    }

    public String getCargo() { return cargo; }

}