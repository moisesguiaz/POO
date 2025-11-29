package model;

public class Restauracao {
    private int id;
    private int idObra;
    private String status; // "andamento" / "finalizada"

    public Restauracao(int id, int idObra, String status) {
        this.id = id;
        this.idObra = idObra;
        this.status = status;
    }

    public int getId() { return id; }
    public int getIdObra() { return idObra; }
    public String getStatus() { return status; }
}
