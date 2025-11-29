package model;

import java.util.List;

public class Visitante {
    private int id;
    private String nome;
    private String login;
    private String senha;

    public Visitante(int id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }

    // Método para visualizar obras
    public void verObras(List<Obra> obras) {
        System.out.println("\n--- Lista de Obras ---");
        for (Obra obra : obras) {
            System.out.println(obra);
        }
    }

    // Método para visualizar exposições
    public void verExposicoes(List<Exposicao> exposicoes) {
        System.out.println("\n--- Lista de Exposições ---");
        for (Exposicao exposicao : exposicoes) {
            System.out.println(exposicao);
        }
    }
}
