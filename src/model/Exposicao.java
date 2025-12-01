package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Exposicao implements Serializable {
    private int id;
    private String titulo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int capacidade;
    private String local;
    private List<Obra> obras;
    private List<Visitante> visitantes;

    public Exposicao(int id, String titulo, LocalDate dataInicio, LocalDate dataFim, int capacidade, String local) {
        this.id = id;
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.capacidade = capacidade;
        this.local = local;
        this.obras = new ArrayList<>();
        this.visitantes = new ArrayList<>();
    }

    public boolean adicionarObra(Obra obra) {
        if (obra.isEmRestauracao()) return false;
        if (obra.isEmExposicao()) return false;
        obras.add(obra);
        obra.setEmExposicao(true);
        return true;
    }

    public void removerObra(Obra obra) {
        if (obras.remove(obra)) {
            obra.setEmExposicao(false);
        }
    }

    public boolean adicionarVisitante(Visitante visitante) {
        if (visitantes.size() >= capacidade) return false;
        visitantes.add(visitante);
        return true;
    }
    
    public void removerVisitante(Visitante visitante) {
        visitantes.remove(visitante);
    }

    // --- Getters e Setters ---
    public int getId() { return id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public List<Obra> getObras() { return obras; }
    public List<Visitante> getVisitantes() { return visitantes; }
}