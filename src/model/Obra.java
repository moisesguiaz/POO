package model;

public class Obra {
    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private boolean emExposicao = false;
    private boolean emRestauracao = false;

    public Obra(int id, String titulo, String autor, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public int getId() { return id; }
    public boolean getEmExposicao() { return emExposicao; }
    public boolean getEmRestauracao() { return emRestauracao; }

    public void setEmExposicao(boolean b) { this.emExposicao = b; }
    public void setEmRestauracao(boolean b) { this.emRestauracao = b; }

    // Método para formatar o ID da obra com dois dígitos
    @Override
    public String toString() {
        return String.format("ID: %02d, Título: %s, Autor: %s, Ano: %d, Em Exposição: %b, Em Restauração: %b",
                             id, titulo, autor, ano, emExposicao, emRestauracao);
    }
}
