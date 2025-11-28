package museum;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Exposicao implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nome;
    private LocalDate data; // single date for simplicity
    private Set<String> obraIds = new HashSet<>();
    private Set<String> participantesVisitors = new HashSet<>();

    public Exposicao(String id, String nome, LocalDate data) {
        this.id = id;
        this.nome = nome;
        this.data = data;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public LocalDate getData() { return data; }
    public Set<String> getObraIds() { return obraIds; }

    public boolean addObra(String obraId) {
        return obraIds.add(obraId);
    }
    public boolean removeObra(String obraId) { return obraIds.remove(obraId); }

    public boolean addParticipante(String visitorId) { return participantesVisitors.add(visitorId); }
    public boolean hasParticipante(String visitorId) { return participantesVisitors.contains(visitorId); }

    @Override
    public String toString() {
        return String.format("Exposicao[id=%s, nome=%s, data=%s, obras=%d]", id, nome, data.toString(), obraIds.size());
    }
}
