package museum;

import java.util.ArrayList;
import java.util.List;

public class Visitor extends User {
    private static final long serialVersionUID = 1L;
    private List<String> inscricoesExposicaoIds = new ArrayList<>();

    public Visitor(String id, String username, String password) {
        super(id, username, password);
    }

    public List<String> getInscricoes() { return inscricoesExposicaoIds; }
    public void addInscricao(String exposicaoId) {
        if (!inscricoesExposicaoIds.contains(exposicaoId)) inscricoesExposicaoIds.add(exposicaoId);
    }
    public void removeInscricao(String exposicaoId) { inscricoesExposicaoIds.remove(exposicaoId); }
}
