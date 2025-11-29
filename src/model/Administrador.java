package model;

import java.util.Date;
import java.util.List;

public class Administrador extends Usuario {

    public Administrador(int id, String nome, String login, String senha) {
        super(id, nome, login, senha);
    }

   
    public void criarObra(List<Obra> obras, String titulo, String artista, int ano, String estadoConservacao) {
        int novoId = obras.stream().mapToInt(Obra::getId).max().orElse(0) + 1;
        Obra novaObra = new Obra(novoId, titulo, artista, ano);
       
        obras.add(novaObra);
        System.out.println("Obra criada com sucesso: " + novaObra);
    }


    public void criarExposicao(List<Exposicao> exposicoes, String titulo, String dataInicio, String dataFim, int capacidade) {
        try {
    
            Date dataInicioExposicao = new Date(dataInicio); 
            Date dataFimExposicao = new Date(dataFim);

            int novoId = exposicoes.stream().mapToInt(Exposicao::getId).max().orElse(0) + 1;
            Exposicao novaExposicao = new Exposicao(novoId, titulo, dataInicioExposicao, dataFimExposicao, capacidade);
            exposicoes.add(novaExposicao);
            System.out.println("Exposição criada com sucesso: " + novaExposicao);
        } catch (Exception e) {
            System.out.println("Erro ao criar exposição: " + e.getMessage());
        }
    }

    public void adicionarObraAExposicao(List<Obra> obras, List<Exposicao> exposicoes, int idObra, int idExposicao) {
        Obra obra = obras.stream().filter(o -> o.getId() == idObra).findFirst().orElse(null);
        Exposicao exposicao = exposicoes.stream().filter(e -> e.getId() == idExposicao).findFirst().orElse(null);

        if (obra != null && exposicao != null) {
            obra.setEmExposicao(true);
            System.out.println("Obra " + obra.getId() + " adicionada à exposição " + exposicao.getId());
        } else {
            System.out.println("Obra ou Exposição não encontrada.");
        }
    }

     public void iniciarRestauracao(List<Obra> obras, int idObra, String dataInicio) {
        Obra obra = obras.stream().filter(o -> o.getId() == idObra).findFirst().orElse(null);
        if (obra != null) {
            obra.setEmRestauracao(true);
            System.out.println("Restauração iniciada para a obra " + obra.getId() + " em " + dataInicio);
        } else {
             System.out.println("Obra não encontrada.");
        }
    }

     public void removerObra(List<Obra> obras, int idObra) {
        Obra obra = obras.stream().filter(o -> o.getId() == idObra).findFirst().orElse(null);
        if (obra != null) {
            if (!obra.getEmExposicao()) {
                obras.remove(obra);
                System.out.println("Obra removida com sucesso.");
            } else {
                System.out.println("Não é possível remover obra em exposição.");
            }
        } else {
            System.out.println("Obra não encontrada.");
        }
    }

    public void deletarVisitante(List<Visitante> visitantes, int idVisitante) {
        Visitante visitante = visitantes.stream().filter(v -> v.getId() == idVisitante).findFirst().orElse(null);
        if (visitante != null) {
            visitantes.remove(visitante);
            System.out.println("Visitante removido com sucesso.");
        } else {
            System.out.println("Visitante não encontrado.");
        }
    }

    public void listarObras(List<Obra> obras) {
        System.out.println("\n--- Lista de Obras ---");
        if (obras.isEmpty()) {
            System.out.println("Nenhuma obra cadastrada.");
        } else {
            for (Obra obra : obras) {
                System.out.println(obra);
            }
        }
    }

    public void listarExposicoes(List<Exposicao> exposicoes) {
        System.out.println("\n--- Lista de Exposições ---");
        if (exposicoes.isEmpty()) {
            System.out.println("Nenhuma exposição cadastrada.");
        } else {
            for (Exposicao exposicao : exposicoes) {
                System.out.println(exposicao);
            }
        }
    }

}