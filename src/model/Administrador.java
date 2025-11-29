package model;
import java.util.Date;
import java.util.List;


public class Administrador {
    private int id;
    private String nome;
    private String login;
    private String senha;

    public Administrador(int id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }

    // Método para criar uma obra de arte
    public void criarObra(String titulo, String artista, int ano, String estadoConservacao) {
        // Criar a obra e exibir mensagem
        System.out.println("Criando obra...");
        System.out.println("Título: " + titulo);
        System.out.println("Artista: " + artista);
        System.out.println("Ano: " + ano);
        System.out.println("Estado de Conservação: " + estadoConservacao);
        System.out.println("Obra criada com sucesso!");
    }

    // Outros métodos para o Administrador (como criar exposição, adicionar obra a exposição, etc.)
    public void criarExposicao(String titulo, String dataInicio, String dataFim, int capacidade) {
        // Convertendo as datas de String para Date
        // Para simplificar, vou usar o formato simples de Data
        Date dataInicioExposicao = new Date(dataInicio);  // Adapte conforme sua implementação de Data
        Date dataFimExposicao = new Date(dataFim);  // Adapte conforme sua implementação de Data

        System.out.println("Criando exposição...");
        System.out.println("Título da Exposição: " + titulo);
        System.out.println("Data de Início: " + dataInicioExposicao);
        System.out.println("Data de Fim: " + dataFimExposicao);
        System.out.println("Capacidade da Exposição: " + capacidade);
        System.out.println("Exposição criada com sucesso!");
    }

    public void adicionarObraAExposicao(int idObra, int idExposicao) {
        System.out.println("Adicionando obra com ID " + idObra + " à exposição com ID " + idExposicao);
        // Aqui você pode adicionar a lógica para associar a obra à exposição,
        // como, por exemplo, armazenar essa relação em uma lista ou banco de dados.
        System.out.println("Obra associada com sucesso à exposição!");
    }

     public void iniciarRestauracao(int idObra, String dataInicio) {
        System.out.println("Iniciando restauração da obra com ID " + idObra);
        System.out.println("Data de início da restauração: " + dataInicio);
        // Aqui, podemos adicionar a lógica para realmente registrar ou atualizar o estado da obra.
        System.out.println("Restauração da obra iniciada com sucesso!");
    }

     public void removerObra(int idObra) {
        // Aqui você pode adicionar a lógica para verificar se a obra está associada a uma exposição ou não
        // Por exemplo, verificando se o ID da obra está presente em uma lista de exposições

        // Se a obra não estiver associada a uma exposição, removê-la
        System.out.println("Removendo a obra com ID " + idObra);
        // Aqui você pode adicionar a lógica para realmente remover a obra do sistema, como removê-la de uma lista ou banco de dados
        System.out.println("Obra com ID " + idObra + " removida com sucesso!");
    }

    public void deletarVisitante(int idVisitante) {
        System.out.println("Deletando visitante com ID " + idVisitante);
        // Lógica de deleção real (por exemplo, removendo da lista ou banco de dados) pode ser adicionada aqui.
        System.out.println("Visitante com ID " + idVisitante + " deletado com sucesso!");
    }

     public void listarObras(List<Obra> obras) {
        System.out.println("\n--- Lista de Obras ---");
        if (obras.isEmpty()) {
            System.out.println("Nenhuma obra cadastrada.");
        } else {
            for (Obra obra : obras) {
                System.out.println(obra);
            }
        }
    }

    public void listarExposicoes(List<Exposicao> exposicoes) {
        System.out.println("\n--- Lista de Exposições ---");
        if (exposicoes.isEmpty()) {
            System.out.println("Nenhuma exposição cadastrada.");
        } else {
            for (Exposicao exposicao : exposicoes) {
                System.out.println(exposicao);
            }
        }
    }

}
