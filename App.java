import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Administrador;
import model.Visitante;
import model.Obra;
import model.Exposicao;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Criação de um administrador de exemplo com o construtor correto
        List<Obra> obras = new ArrayList<>();
        obras.add(new Obra(1, "Mona Lisa", "Leonardo da Vinci", 1503));
        obras.add(new Obra(2, "Starry Night", "Vincent van Gogh", 1889));

        List<Exposicao> exposicoes = new ArrayList<>();
        exposicoes.add(new Exposicao(1, "Arte Clássica", new java.util.Date(), new java.util.Date(), 50));
        exposicoes.add(new Exposicao(2, "Impressionismo", new java.util.Date(), new java.util.Date(), 40));

        Administrador admin = new Administrador(1, "João", "admin", "admin123");
        Visitante visitante = new Visitante(1, "Maria", "visitante", "senha123");
        
        
        while (true) {
            // Menu Principal
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1) Entrar como Administrador");
            System.out.println("2) Entrar como Visitante");
            System.out.println("0) Sair");
            System.out.print("> ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            if (opcao == 1) {
                // Entrar como Administrador
                System.out.println("Digite seu login:");
                String login = scanner.nextLine();
                System.out.println("Digite sua senha:");
                String senha = scanner.nextLine();
                
                if (admin.getLogin().equals(login) && admin.getSenha().equals(senha)) {
                    mostrarMenu(admin, "Administrador");
                } else {
                    System.out.println("Login ou senha incorretos.");
                }
            } else if (opcao == 2) {
                // Entrar como Visitante
                mostrarMenu(null, "Visitante");
            } else if (opcao == 0) {
                System.out.println("Saindo...");
                break; // Encerra o programa
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    // Método que mostra o menu de opções de acordo com o tipo de usuário
    private static void mostrarMenu(Administrador admin, String tipoUsuario) {
        while (true) {
            System.out.println("\n--- Menu " + tipoUsuario + " ---");
            
            if (tipoUsuario.equals("Administrador")) {
                // Opções para Administrador
                System.out.println("1) Criar obra");
                System.out.println("2) Criar exposição");
                System.out.println("3) Adicionar obra a exposição");
                System.out.println("4) Iniciar restauração");
                System.out.println("5) Remover obra (se não ligada)");
                System.out.println("6) Deletar visitante por id");
                System.out.println("7) Listar obras e exposições");
            } else if (tipoUsuario.equals("Visitante")) {
                // Opções para Visitante
                System.out.println("1) Ver obras");
                System.out.println("2) Ver exposições");
            }

            System.out.println("0) Logout");
            System.out.print("> ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            if (opcao == 0) {
                System.out.println("Logout realizado com sucesso.");
                return; // Sai do menu
            }

            if (tipoUsuario.equals("Administrador")) {
                // Executa opções do Administrador
                switch (opcao) {
                    case 1:
                     // Criar obra
                        System.out.print("Digite o título da obra: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Digite o artista da obra: ");
                        String artista = scanner.nextLine();
                        System.out.print("Digite o ano da obra: ");
                        int ano = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer
                        System.out.print("Digite o estado de conservação: ");
                        String estadoConservacao = scanner.nextLine();
                        admin.criarObra(titulo, artista, ano, estadoConservacao);
                        break;
                    case 2:
                        // Criar exposição
                        System.out.print("Digite o título da exposição: ");
                        String tituloExposicao = scanner.nextLine();
                        System.out.print("Digite a data de início (dd/MM/yyyy): ");
                        String dataInicio = scanner.nextLine();
                        System.out.print("Digite a data de fim (dd/MM/yyyy): ");
                        String dataFim = scanner.nextLine();
                        System.out.print("Digite a capacidade da exposição: ");
                        int capacidade = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer
                        admin.criarExposicao(tituloExposicao, dataInicio, dataFim, capacidade);
                        break;
                    case 3:
                        // Adicionar obra a exposição
                        System.out.print("Digite o ID da obra: ");
                        int idObra = scanner.nextInt();
                        System.out.print("Digite o ID da exposição: ");
                        int idExposicao = scanner.nextInt();
                        admin.adicionarObraAExposicao(idObra, idExposicao);
                        break;
                    case 4:
                         // Iniciar restauração
                        System.out.print("Digite o ID da obra a ser restaurada: ");
                        int idObraRestaura = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer
                        System.out.print("Digite a data de início da restauração (dd/MM/yyyy): ");
                        String dataRestauracao = scanner.nextLine();
                        admin.iniciarRestauracao(idObraRestaura, dataRestauracao);
                        break;
                    case 5:
                        // Remover obra
                        System.out.print("Digite o ID da obra a ser removida: ");
                        int idObraRemover = scanner.nextInt();
                        admin.removerObra(idObraRemover);
                        break;
                    case 6:
                        System.out.print("Digite o ID do visitante a ser deletado: ");
                        int idVisitante = scanner.nextInt();
                        admin.deletarVisitante(idVisitante);
                        break;
                    case 7:
    
                        break;
                        

                     default:
                        System.out.println("Opção inválida! Tente novamente.");
                }

            
            }
        }
    }
}
