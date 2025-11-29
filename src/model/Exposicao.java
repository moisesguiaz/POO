package model;

import java.util.Date;

public class Exposicao {
    private int id;
    private String titulo;
    private Date dataInicio;
    private Date dataFim;
    private int capacidade;

    public Exposicao(int id, String titulo, Date dataInicio, Date dataFim, int capacidade) {
        this.id = id;
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.capacidade = capacidade;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public Date getDataInicio() { return dataInicio; }
    public Date getDataFim() { return dataFim; }
    public int getCapacidade() { return capacidade; }

    // Método para formatar o ID da exposição com dois dígitos
    @Override
    public String toString() {
        return String.format("ID: %02d, Título: %s, Início: %s, Fim: %s, Capacidade: %d",
                             id, titulo, dataInicio, dataFim, capacidade);
    }
}
