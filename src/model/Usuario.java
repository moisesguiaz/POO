package model;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    protected int id;
    protected String nome;
    protected String login;
    protected String senha;
    protected String cpf;

    public Usuario(int id, String nome, String login, String senha, String cpf) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
    }

    // Método comum para Visitante e Administrador
    public void editarPerfil(String novoNome, String novaSenha) {
        if (novoNome != null && !novoNome.isEmpty()) {
            this.nome = novoNome;
        }
        if (novaSenha != null && !novaSenha.isEmpty()) {
            this.senha = novaSenha;
        }
        System.out.println("Perfil atualizado com sucesso!");
    }

    // --- Getters e Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}