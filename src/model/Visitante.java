package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Visitante extends Usuario implements Serializable {
    private String email;
    private String endereco;
    private String genero;
    private String telefone;
    private List<Exposicao> inscricoes;

    public Visitante(int id, String nome, String login, String senha, String cpf, 
                     String email, String endereco, String genero, String telefone) {
        super(id, nome, login, senha, cpf);
        this.email = email;
        this.endereco = endereco;
        this.genero = genero;
        this.telefone = telefone;
        this.inscricoes = new ArrayList<>();
    }

    public boolean ingressarEmExposicao(Exposicao novaExposicao) {
        if (inscricoes.contains(novaExposicao)) {
            System.out.println("Erro: Você já está inscrito nesta exposição.");
            return false;
        }
        for (Exposicao exp : inscricoes) {
            if (novaExposicao.getDataInicio().isBefore(exp.getDataFim()) && 
                novaExposicao.getDataFim().isAfter(exp.getDataInicio())) {
                System.out.println("Erro: Conflito de horário com '" + exp.getTitulo() + "'.");
                return false;
            }
        }
        if (novaExposicao.adicionarVisitante(this)) {
            this.inscricoes.add(novaExposicao);
            System.out.println("Inscrição realizada com sucesso!");
            return true;
        }
        return false;
    }

    public void cancelarIngresso(Exposicao exposicao) {
        if (inscricoes.remove(exposicao)) {
            exposicao.removerVisitante(this);
            System.out.println("Inscrição cancelada.");
        } else {
            System.out.println("Você não estava inscrito.");
        }
    }

    public void listarInscricoes() {
        System.out.println("--- Minhas Inscrições ---");
        if (inscricoes.isEmpty()) System.out.println("Nenhuma.");
        else for (Exposicao exp : inscricoes) System.out.println("- " + exp.getTitulo());
    }

    // Getters
    public String getEmail() { return email; }
    public String getEndereco() { return endereco; }
    public String getGenero() { return genero; }
    public String getTelefone() { return telefone; }
}