import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Administrador;
import model.Exposicao;
import model.Obra;
import model.Restauracao;
import model.Usuario;
import model.Visitante;

public class App {
    // Listas Globais ("Banco de Dados" em memória RAM)
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Obra> obras = new ArrayList<>();
    private static List<Exposicao> exposicoes = new ArrayList<>();
    private static List<Restauracao> restauracoes = new ArrayList<>();
    
    // Nome do arquivo físico
    private static final String ARQUIVO_DADOS = "museu_dados.bin";
    
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        carregarDados();

        while (true) {
            System.out.println("\n=== MUSEU SYSTEM - MENU PRINCIPAL ===");
            System.out.println("1. Login");
            System.out.println("2. Cadastrar novo Visitante");
            System.out.println("0. Sair e Salvar");
            
            // BLINDAGEM: Usamos o método seguro aqui também
            int opcao = lerInteiro("Opção: ");

            switch (opcao) {
                case 1: // Note que agora é o número 1, sem aspas
                    fazerLogin(); 
                    break;
                case 2: 
                    cadastrarVisitante(); 
                    break;
                case 0:
                    try {
                        salvarDados();
                        System.out.println("Dados salvos em '" + ARQUIVO_DADOS + "'.");
                    } catch (IOException e) {
                        System.out.println("Erro crítico ao salvar: " + e.getMessage());
                    }
                    return;
                default: 
                    System.out.println("Opção inválida.");
            }
        }
    }

    // --- PERSISTÊNCIA DE DADOS (ARQUIVOS) ---

    private static void salvarDados() throws IOException {
        File arquivo = new File(ARQUIVO_DADOS);
        System.out.println(">>> Salvando dados em: " + arquivo.getAbsolutePath());
        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(usuarios);
            out.writeObject(obras);
            out.writeObject(exposicoes);
            out.writeObject(restauracoes);
        }
    }

    @SuppressWarnings("unchecked")
    private static void carregarDados() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS))) {
            usuarios = (List<Usuario>) in.readObject();
            obras = (List<Obra>) in.readObject();
            exposicoes = (List<Exposicao>) in.readObject();
            restauracoes = (List<Restauracao>) in.readObject();
            System.out.println(">> Base de dados carregada com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println(">> Nenhuma base de dados encontrada. Iniciando com dados padrão.");
            inicializarDadosPadrao();
        } catch (Exception e) {
            System.out.println(">> Erro ao ler arquivo (pode estar corrompido). Iniciando do zero.");
            inicializarDadosPadrao();
        }
    }

    private static void inicializarDadosPadrao() {
        // Cria apenas o Administrador padrão se o sistema estiver vazio
        Administrador admin = new Administrador(1, "Admin Chefe", "admin", "1234", "000.000.000-00", "Gerente Geral");
        usuarios.add(admin);
    }

    // --- CADASTROS E LOGIN ---

    private static void cadastrarVisitante() {
        System.out.println("\n--- Cadastro de Visitante ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        // --- VERIFICAÇÃO DE LOGIN ÚNICO ---
        String login;
        while (true) {
            System.out.print("Login: ");
            login = scanner.nextLine();
            
            boolean existe = false;
            for (Usuario u : usuarios) {
                if (u.getLogin().equals(login)) {
                    existe = true;
                    break;
                }
            }
            
            if (existe) {
                System.out.println("Erro: Este login já está em uso. Escolha outro.");
            } else {
                break; // Login é único, pode sair do loop
            }
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        int id = usuarios.size() + 1; // Gera ID
        Visitante novo = new Visitante(id, nome, login, senha, cpf, email, "ND", "ND", "ND");
        usuarios.add(novo);
        System.out.println("Visitante cadastrado! Faça login.");
    }

    private static void fazerLogin() {
        System.out.print("Login: "); String login = scanner.nextLine();
        System.out.print("Senha: "); String senha = scanner.nextLine();

        Usuario usuarioLogado = null;
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                usuarioLogado = u;
                break;
            }
        }

        if (usuarioLogado == null) {
            System.out.println("Login ou senha incorretos.");
            return;
        }

        System.out.println("Bem-vindo, " + usuarioLogado.getNome() + "!");

        if (usuarioLogado instanceof Administrador) {
            menuAdministrador((Administrador) usuarioLogado);
        } else if (usuarioLogado instanceof Visitante) {
            menuVisitante((Visitante) usuarioLogado);
        }
    }

    // --- MENUS ---

    private static void menuAdministrador(Administrador admin) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("       PAINEL ADMINISTRATIVO");
            System.out.println("========================================");
            
            System.out.println("--- GESTÃO DE OBRAS ---");
            System.out.println("1.  Cadastrar Nova Obra");
            System.out.println("2.  Listar Obras (Acervo)");
            System.out.println("3.  Editar Obra");
            System.out.println("4.  Excluir Obra");
            System.out.println("5.  Iniciar Restauração");
            System.out.println("6.  Concluir Restauração");

            System.out.println("\n--- GESTÃO DE EXPOSIÇÕES ---");
            System.out.println("7.  Criar Nova Exposição");
            System.out.println("8.  Listar Exposições");
            System.out.println("9.  Editar Exposição");
            System.out.println("10. Excluir Exposição");
            System.out.println("11. Adicionar Obra em Exposição");
            System.out.println("12. Remover Obra de Exposição");

            System.out.println("\n--- SISTEMA ---");
            System.out.println("13. Gerenciar Visitantes");
            System.out.println("14. Editar meu Perfil");
            System.out.println("0.  Sair");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            if (op.equals("0")) break;

            switch (op) {
                // --- OBRAS ---
                case "1":
                    System.out.print("Título: "); String tit = scanner.nextLine();
                    System.out.print("Artista: "); String art = scanner.nextLine();
                    // AQUI: Uso do lerInteiro para proteger
                    int ano = lerInteiro("Ano: ");
                    System.out.print("Estilo: "); String est = scanner.nextLine();
                    System.out.print("Conservação: "); String cons = scanner.nextLine();
                    admin.criarObra(obras, tit, art, ano, est, 0.0, cons);
                    break;
                case "2":
                    System.out.println("\n--- Acervo ---");
                    for(Obra o : obras) System.out.println(o);
                    break;
                case "3":
                    int idEdit = lerInteiro("ID da obra para editar: ");
                    System.out.print("Novo Título: "); String nTit = scanner.nextLine();
                    System.out.print("Novo Artista: "); String nArt = scanner.nextLine();
                    int nAno = lerInteiro("Novo Ano: ");
                    System.out.print("Conservação: "); String nCons = scanner.nextLine();
                    admin.editarObra(obras, idEdit, nTit, nArt, nAno, nCons);
                    break;
                case "4": 
                    int idExcluir = lerInteiro("ID da obra para excluir: ");
                    admin.excluirObra(obras, idExcluir); 
                    break;
                case "5":
                    int idObra = lerInteiro("ID da Obra para restaurar: ");
                    Obra o = buscarObra(idObra);
                    if (o != null) admin.iniciarRestauracao(restauracoes, exposicoes, o, LocalDate.now());
                    else System.out.println("Obra não encontrada.");
                    break;
                case "6": 
                    int idObraRes = lerInteiro("ID da Obra para finalizar restauração: ");
                    admin.finalizarRestauracao(restauracoes, idObraRes, LocalDate.now());
                    break;

                // --- EXPOSIÇÕES ---
                case "7":
                    System.out.print("Título Expo: "); String titExp = scanner.nextLine();
                    LocalDate ini = lerData("Data Início (dd/MM/yyyy): ");
                    LocalDate fim = lerData("Data Fim (dd/MM/yyyy): ");
                    int cap = lerInteiro("Capacidade: ");
                    if (ini != null && fim != null) 
                        admin.criarExposicao(exposicoes, titExp, ini, fim, cap, "Salão Principal");
                    break;
                case "8":
                    System.out.println("\n--- Exposições ---");
                    for(Exposicao e : exposicoes) {
                        System.out.println("ID: " + e.getId() + " - " + e.getTitulo() + 
                                           " (" + e.getObras().size() + " obras)");
                    }
                    break;
                case "9": // Editar Exposição
                    int idEd = lerInteiro("ID Expo para editar: ");
                    System.out.print("Novo Título: "); 
                    String nTitExp = scanner.nextLine();
                    
                    // CORREÇÃO: Passando apenas 4 argumentos para casar com o Administrador.java
                    admin.editarExposicao(exposicoes, idEd, nTitExp, 0); 
                    break;
                case "10":
                    int idExcluirExp = lerInteiro("ID Expo para excluir: ");
                    admin.excluirExposicao(exposicoes, idExcluirExp);
                    break;
                case "11":
                    adicionarObraExposicao(admin);
                    break;
                case "12":
                    int ideR = lerInteiro("ID Expo: ");
                    int idoR = lerInteiro("ID Obra: ");
                    Exposicao exR = buscarExposicao(ideR);
                    Obra obR = buscarObra(idoR);
                    if(exR != null && obR != null) admin.removerObraDaExposicao(exR, obR);
                    else System.out.println("Não encontrado.");
                    break;

                // --- SISTEMA ---
                case "13":
                    admin.listarVisitantes(usuarios);
                    System.out.println("[D] Deletar um visitante | [V] Voltar");
                    if (scanner.nextLine().equalsIgnoreCase("D")) {
                        int idVis = lerInteiro("ID do Visitante: ");
                        admin.excluirVisitante(usuarios, idVis);
                    }
                    break;
                case "14":
                    System.out.print("Novo Nome: "); String nNome = scanner.nextLine();
                    System.out.print("Nova Senha: "); String nSenha = scanner.nextLine();
                    admin.editarPerfil(nNome, nSenha);
                    break;

                default: System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuVisitante(Visitante visitante) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("           ÁREA DO VISITANTE");
            System.out.println("========================================");

            System.out.println("--- EXPLORAÇÃO DO MUSEU ---");
            System.out.println("1. Ver Exposições Disponíveis");
            System.out.println("2. Ver Acervo Completo (Todas as Obras)");
            System.out.println("3. Ver Detalhes de uma Exposição (Obras)");

            System.out.println("\n--- MINHAS INSCRIÇÕES ---");
            System.out.println("4. Inscrever-se em Exposição");
            System.out.println("5. Ver Minhas Inscrições");
            System.out.println("6. Cancelar Inscrição");

            System.out.println("\n--- MEU PERFIL ---");
            System.out.println("7. Editar meus Dados");
            System.out.println("8. Excluir minha Conta");
            System.out.println("0. Sair");
            
            // BLINDAGEM: Usamos lerInteiro para ler a opção do menu
            int op = lerInteiro("Opção: ");

            if (op == 0) break;

            switch (op) {
                // --- EXPLORAÇÃO ---
                case 1:
                    System.out.println("\n--- Exposições ---");
                    for(Exposicao e : exposicoes) {
                        System.out.println("ID: " + e.getId() + " | " + e.getTitulo() + 
                                           " | " + e.getDataInicio() + " até " + e.getDataFim());
                    }
                    break;

                case 2: // Ver Acervo
                    System.out.println("\n--- Acervo do Museu ---");
                    if (obras.isEmpty()) {
                        System.out.println("Nenhuma obra cadastrada no sistema.");
                    } else {
                        for(Obra o : obras) System.out.println(o);
                    }
                    break;

                case 3: // Ver Obras de uma Expo
                    // BLINDAGEM: lerInteiro em vez de parseInt direto
                    int idVer = lerInteiro("Digite o ID da exposição para ver as obras: ");
                    Exposicao expoVer = buscarExposicao(idVer);
                    
                    if (expoVer != null) {
                        System.out.println("\n--- Obras na Exposição: " + expoVer.getTitulo() + " ---");
                        if (expoVer.getObras().isEmpty()) {
                            System.out.println("Esta exposição ainda não tem obras.");
                        } else {
                            for (Obra o : expoVer.getObras()) {
                                System.out.println("- " + o);
                            }
                        }
                    } else {
                        System.out.println("Exposição não encontrada.");
                    }
                    break;

                // --- INSCRIÇÕES ---
                case 4: // Inscrever-se
                    int idExp = lerInteiro("Digite o ID da exposição: ");
                    Exposicao alvo = buscarExposicao(idExp);
                    if (alvo != null) visitante.ingressarEmExposicao(alvo);
                    else System.out.println("Exposição não encontrada.");
                    break;
                
                case 5: // Ver Inscrições
                    visitante.listarInscricoes();
                    break;
                
                case 6: // Cancelar Inscrição
                    int idCanc = lerInteiro("ID da exposição para cancelar: ");
                    Exposicao expCanc = buscarExposicao(idCanc);
                    if (expCanc != null) visitante.cancelarIngresso(expCanc);
                    else System.out.println("Exposição não encontrada.");
                    break;

                // --- PERFIL ---
                case 7: // Editar Perfil (Inputs de texto mantêm scanner.nextLine)
                    System.out.print("Novo Nome (enter para manter): ");
                    String nNome = scanner.nextLine();
                    System.out.print("Nova Senha (enter para manter): ");
                    String nSenha = scanner.nextLine();
                    visitante.editarPerfil(nNome, nSenha);
                    break;

                case 8: // Excluir Conta
                    System.out.print("Tem certeza que deseja excluir sua conta? (S/N): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("S")) {
                        usuarios.remove(visitante);
                        System.out.println("Conta excluída com sucesso. Adeus!");
                        return; // Logout forçado
                    }
                    break;

                default: System.out.println("Opção inválida.");
            }
        }
    }

    // --- MÉTODOS AUXILIARES ---

    private static void adicionarObraExposicao(Administrador admin) {
        // BLINDAGEM: Trocamos Scanner por lerInteiro
        int idObra = lerInteiro("ID da Obra: ");
        int idExp = lerInteiro("ID da Exposição: ");

        Obra o = buscarObra(idObra);
        Exposicao e = buscarExposicao(idExp);

        if (o != null && e != null) {
            admin.incluirObraEmExposicao(e, o);
        } else {
            System.out.println("Obra ou Exposição não encontrada.");
        }
    }

    private static void iniciarRestauracao(Administrador admin) {
        System.out.print("ID da Obra: ");
        int idObra = Integer.parseInt(scanner.nextLine());
        Obra o = buscarObra(idObra);
        if (o != null) {
            admin.iniciarRestauracao(restauracoes, exposicoes, o, LocalDate.now());
        } else {
            System.out.println("Obra não encontrada.");
        }
    }

    private static LocalDate lerData(String msg) {
        System.out.print(msg);
        try {
            return LocalDate.parse(scanner.nextLine(), fmt);
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida! Use o formato dd/MM/yyyy");
            return null;
        }
    }

    private static Obra buscarObra(int id) {
        for (Obra o : obras) if (o.getId() == id) return o;
        return null;
    }

    private static Exposicao buscarExposicao(int id) {
        for (Exposicao e : exposicoes) if (e.getId() == id) return e;
        return null;
    }

    // --- MÉTODO NOVO PARA BLINDAR NÚMEROS ---
    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                // Tenta ler o que o usuário digitou e converter para número
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // SE DER ERRO (ex: digitou "abc"), cai aqui e pede de novo
                System.out.println("Erro: Valor inválido! Digite apenas números.");
            }
        }
    }
}