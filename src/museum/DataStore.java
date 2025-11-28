package museum;

import java.io.*;
import java.util.*;

public class DataStore {
    private static final String USERS_FILE = "data/users.dat";
    private static final String OBRAS_FILE = "data/obras.dat";
    private static final String EXPOS_FILE = "data/expos.dat";
    private static final String REST_FILE = "data/rests.dat";

    public Map<String, User> users = new HashMap<>();
    public Map<String, Obra> obras = new HashMap<>();
    public Map<String, Exposicao> exposicoes = new HashMap<>();
    public Map<String, Restauracao> restauracoes = new HashMap<>();

    public DataStore() {
        loadAll();
    }

    @SuppressWarnings("unchecked")
    private <T> Map<String, T> load(String path) {
        File f = new File(path);
        if (!f.exists()) return new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object o = ois.readObject();
            if (o instanceof Map) return (Map<String, T>) o;
        } catch (Exception e) {
            System.err.println("Falha ao carregar " + path + ": " + e.getMessage());
        }
        return new HashMap<>();
    }

    private <T> void save(String path, Map<String, T> map) {
        File f = new File(path);
        f.getParentFile().mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(map);
        } catch (Exception e) {
            System.err.println("Falha ao salvar " + path + ": " + e.getMessage());
        }
    }

    public void loadAll() {
        users = load(USERS_FILE);
        obras = load(OBRAS_FILE);
        exposicoes = load(EXPOS_FILE);
        restauracoes = load(REST_FILE);
    }

    public void saveAll() {
        save(USERS_FILE, users);
        save(OBRAS_FILE, obras);
        save(EXPOS_FILE, exposicoes);
        save(REST_FILE, restauracoes);
    }
}
