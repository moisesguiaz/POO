package museum;

import java.io.Serializable;

public class Obra implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String titulo;
    private String artista;
    private String exposicaoId; // id of exposition where it's placed (nullable)
    private String restauracaoId; // id of restoration (nullable)

    public Obra(String id, String titulo, String artista) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public String getExposicaoId() { return exposicaoId; }
    public void setExposicaoId(String exposicaoId) { this.exposicaoId = exposicaoId; }
    public String getRestauracaoId() { return restauracaoId; }
    public void setRestauracaoId(String restauracaoId) { this.restauracaoId = restauracaoId; }

    public boolean isInExhibition() { return exposicaoId != null; }
    public boolean isInRestoration() { return restauracaoId != null; }

    @Override
    public String toString() {
        return String.format("Obra[id=%s, titulo=%s, artista=%s, exposicao=%s, restauracao=%s]",
            id, titulo, artista, exposicaoId, restauracaoId);
    }
}
