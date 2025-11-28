package museum;

import java.io.Serializable;

public class Restauracao implements Serializable {
    private static final long serialVersionUID = 1L;
    public enum Status { PENDING, IN_PROGRESS, COMPLETED }

    private String id;
    private String obraId;
    private Status status;

    public Restauracao(String id, String obraId) {
        this.id = id;
        this.obraId = obraId;
        this.status = Status.IN_PROGRESS;
    }

    public String getId() { return id; }
    public String getObraId() { return obraId; }
    public Status getStatus() { return status; }
    public void setStatus(Status s) { this.status = s; }

    @Override
    public String toString() {
        return String.format("Restauracao[id=%s, obra=%s, status=%s]", id, obraId, status);
    }
}
