package model;

import java.io.Serializable;

public class Obra implements Serializable {
    private int id;
    private String titulo;
    private String artista;
    private int ano;
    private String estilo;
    private double valorEstimado;
    private String localizacaoAtual;
    private String estadoConservacao;
    private String status; 

    public Obra(int id, String titulo, String artista, int ano, String estilo, 
                double valorEstimado, String localizacaoAtual, String estadoConservacao) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.ano = ano;
        this.estilo = estilo;
        this.valorEstimado = valorEstimado;
        this.localizacaoAtual = localizacaoAtual;
        this.estadoConservacao = estadoConservacao;
        this.status = "Disponível"; 
    }

    // --- Métodos de Lógica ---
    public boolean isEmExposicao() {
        return "Em Exposição".equalsIgnoreCase(this.status);
    }

    public boolean isEmRestauracao() {
        return "Em Restauração".equalsIgnoreCase(this.status);
    }

    public void setEmExposicao(boolean ativo) {
        if (ativo) {
            this.status = "Em Exposição";
            this.localizacaoAtual = "Exposição";
        } else {
            this.status = "Disponível";
            this.localizacaoAtual = "Acervo";
        }
    }

    public void setEmRestauracao(boolean ativo) {
        if (ativo) {
            this.status = "Em Restauração";
            this.localizacaoAtual = "Laboratório de Restauro";
        } else {
            this.status = "Disponível";
            this.localizacaoAtual = "Acervo";
        }
    }

    // --- Getters e Setters ---
    public int getId() { return id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; } 

    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; } 

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; } 

    public String getEstilo() { return estilo; }
    public void setEstilo(String estilo) { this.estilo = estilo; }

    public double getValorEstimado() { return valorEstimado; }
    public void setValorEstimado(double valorEstimado) { this.valorEstimado = valorEstimado; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLocalizacaoAtual() { return localizacaoAtual; }
    public void setLocalizacaoAtual(String localizacaoAtual) { this.localizacaoAtual = localizacaoAtual; }

    public String getEstadoConservacao() { return estadoConservacao; }
    public void setEstadoConservacao(String estadoConservacao) { this.estadoConservacao = estadoConservacao; }

    @Override
    public String toString() {
<<<<<<< HEAD
        return String.format("ID: %d | %s - %s (%d) | Status: %s | Local: %s", 
                id, titulo, artista, ano, status, localizacaoAtual);
=======
        return String.format("ID: %02d, Título: %s, Autor: %s, Ano: %d, Em Exposição: %b, Em Restauração: %b",
        id, titulo, autor, ano, emExposicao, emRestauracao);
>>>>>>> 7efe39e88c5a8aff52aa469e3ffce85bf0a719dd
    }
}