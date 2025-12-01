package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Restauracao implements Serializable {
    private int id;
    private Obra obra;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status; 

    public Restauracao(int id, Obra obra, LocalDate dataInicio) {
        this.id = id;
        this.obra = obra;
        this.dataInicio = dataInicio;
        this.status = "Em andamento";
        this.obra.setEmRestauracao(true);
    }

    public void concluirRestauracao(LocalDate dataFim) {
        if (this.status.equals("Concluída")) {
            System.out.println("Esta restauração já foi concluída.");
            return;
        }
        this.dataFim = dataFim;
        this.status = "Concluída";
        this.obra.setEmRestauracao(false);
        System.out.println("Restauração concluída.");
    }
    
    public int getId() { return id; }
    public Obra getObra() { return obra; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Restauração [Obra: " + obra.getTitulo() + ", Início: " + dataInicio + ", Status: " + status + "]";
    }
}