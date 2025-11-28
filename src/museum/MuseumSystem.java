package museum;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MuseumSystem {
    private DataStore store;

    public MuseumSystem(DataStore store) {
        this.store = store;
        ensureDefaultAdmin();
    }

    private void ensureDefaultAdmin() {
        // create admin if none exists
        boolean hasAdmin = store.users.values().stream().anyMatch(u -> u instanceof Administrator);
        if (!hasAdmin) {
            Administrator admin = new Administrator(UUID.randomUUID().toString(), "admin", "admin");
            store.users.put(admin.getId(), admin);
            store.saveAll();
            System.out.println("Administrador padrão criado: usuário=admin senha=admin");
        }
    }

    // Registration
    public Visitor registerVisitor(String username, String password) {
        String id = UUID.randomUUID().toString();
        Visitor v = new Visitor(id, username, password);
        store.users.put(id, v);
        store.saveAll();
        return v;
    }

    // Login
    public User login(String username, String password) {
        for (User u : store.users.values()) {
            if (u.getUsername().equals(username) && u.checkPassword(password)) return u;
        }
        return null;
    }

    // Admin actions
    public Obra createObra(String titulo, String artista) {
        String id = UUID.randomUUID().toString();
        Obra o = new Obra(id, titulo, artista);
        store.obras.put(id, o);
        store.saveAll();
        return o;
    }

    public Exposicao createExposicao(String nome, LocalDate date) {
        String id = UUID.randomUUID().toString();
        Exposicao e = new Exposicao(id, nome, date);
        store.exposicoes.put(id, e);
        store.saveAll();
        return e;
    }

    // Add obra to exposicao with checks
    public boolean addObraToExposicao(String obraId, String exposicaoId) {
        Obra o = store.obras.get(obraId);
        Exposicao e = store.exposicoes.get(exposicaoId);
        if (o == null || e == null) return false;
        if (o.isInExhibition() || o.isInRestoration()) return false;
        boolean added = e.addObra(obraId);
        if (added) {
            o.setExposicaoId(exposicaoId);
            store.saveAll();
        }
        return added;
    }

    public boolean removeObra(String obraId) {
        Obra o = store.obras.get(obraId);
        if (o == null) return false;
        if (o.isInExhibition() || o.isInRestoration()) return false;
        store.obras.remove(obraId);
        store.saveAll();
        return true;
    }

    public Restauracao startRestauracao(String obraId) {
        Obra o = store.obras.get(obraId);
        if (o == null) return null;
        if (o.isInRestoration()) return null;
        // remove from exposition if present
        if (o.isInExhibition()) {
            Exposicao e = store.exposicoes.get(o.getExposicaoId());
            if (e != null) {
                e.removeObra(obraId);
            }
            o.setExposicaoId(null);
        }
        String id = UUID.randomUUID().toString();
        Restauracao r = new Restauracao(id, obraId);
        store.restauracoes.put(id, r);
        o.setRestauracaoId(id);
        store.saveAll();
        return r;
    }

    public boolean deleteVisitor(String visitorId) {
        User u = store.users.get(visitorId);
        if (u == null || !(u instanceof Visitor)) return false;
        // remove their participations
        Visitor v = (Visitor) u;
        for (String exposId : new ArrayList<>(v.getInscricoes())) {
            Exposicao e = store.exposicoes.get(exposId);
            if (e != null) {
                e.getObraIds(); // no direct action needed for participant removal in this simplified version
            }
        }
        store.users.remove(visitorId);
        store.saveAll();
        return true;
    }

    // Visitor actions
    public List<Obra> listObras() {
        return new ArrayList<>(store.obras.values());
    }

    public List<Exposicao> listExposicoes() {
        return new ArrayList<>(store.exposicoes.values());
    }

    public boolean subscribeVisitorToExposicao(String visitorId, String exposicaoId) {
        Visitor v = (Visitor) store.users.get(visitorId);
        Exposicao e = store.exposicoes.get(exposicaoId);
        if (v == null || e == null) return false;
        // check date conflict: visitor cannot be registered to two exposicoes same date
        LocalDate targetDate = e.getData();
        for (String otherExpId : v.getInscricoes()) {
            Exposicao other = store.exposicoes.get(otherExpId);
            if (other != null && other.getData().equals(targetDate)) return false;
        }
        boolean added = e.addParticipante(visitorId);
        if (added) {
            v.addInscricao(exposicaoId);
            store.saveAll();
        }
        return added;
    }

    public boolean visitorDeleteOwnAccount(String visitorId) {
        return deleteVisitor(visitorId);
    }

    public Map<String, Obra> getObrasMap() { return store.obras; }
    public Map<String, Exposicao> getExposicoesMap() { return store.exposicoes; }
    public Map<String, User> getUsersMap() { return store.users; }
    public Map<String, Restauracao> getRestauracoesMap() { return store.restauracoes; }
}
