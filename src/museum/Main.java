package museum;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        DataStore ds = new DataStore();
        MuseumSystem sys = new MuseumSystem(ds);

        while (true) {
            System.out.println("\n--- Museu ---");
            System.out.println("1) Registrar visitante");
            System.out.println("2) Login");
            System.out.println("0) Sair");
            System.out.print("> ");
            String opt = scanner.nextLine().trim();
            if (opt.equals("1")) {
                System.out.print("Usuário: ");
                String u = scanner.nextLine().trim();
                System.out.print("Senha: ");
                String p = scanner.nextLine().trim();
                Visitor v = sys.registerVisitor(u, p);
                System.out.println("Registrado com id: " + v.getId());
            } else if (opt.equals("2")) {
                System.out.print("Usuário: ");
                String u = scanner.nextLine().trim();
                System.out.print("Senha: ");
                String p = scanner.nextLine().trim();
                User user = sys.login(u, p);
                if (user == null) {
                    System.out.println("Credenciais inválidas");
                } else {
                    if (user instanceof Administrator) adminMenu((Administrator)user, sys);
                    else if (user instanceof Visitor) visitorMenu((Visitor)user, sys);
                }
            } else if (opt.equals("0")) {
                System.out.println("Saindo...");
                ds.saveAll();
                break;
            }
        }
    }

    private static void adminMenu(Administrator admin, MuseumSystem sys) {
        while (true) {
            System.out.println("\n--- Menu Administrador ---");
            System.out.println("1) Criar obra");
            System.out.println("2) Criar exposição");
            System.out.println("3) Adicionar obra a exposição");
            System.out.println("4) Iniciar restauração");
            System.out.println("5) Remover obra (se não ligada)");
            System.out.println("6) Deletar visitante por id");
            System.out.println("7) Listar obras e exposições");
            System.out.println("0) Logout");
            System.out.print("> ");
            String opt = scanner.nextLine().trim();
            try {
                if (opt.equals("1")) {
                    System.out.print("Título: "); String t = scanner.nextLine().trim();
                    System.out.print("Artista: "); String a = scanner.nextLine().trim();
                    Obra o = sys.createObra(t, a);
                    System.out.println("Obra criada id=" + o.getId());
                } else if (opt.equals("2")) {
                    System.out.print("Nome da exposição: "); String n = scanner.nextLine().trim();
                    System.out.print("Data (YYYY-MM-DD): "); String d = scanner.nextLine().trim();
                    Exposicao e = sys.createExposicao(n, LocalDate.parse(d));
                    System.out.println("Exposição criada id=" + e.getId());
                } else if (opt.equals("3")) {
                    System.out.print("Id obra: "); String oid = scanner.nextLine().trim();
                    System.out.print("Id exposicao: "); String eid = scanner.nextLine().trim();
                    boolean ok = sys.addObraToExposicao(oid, eid);
                    System.out.println(ok ? "Adicionado" : "Falha (verifique condições)");
                } else if (opt.equals("4")) {
                    System.out.print("Id obra: "); String oid = scanner.nextLine().trim();
                    Restauracao r = sys.startRestauracao(oid);
                    System.out.println(r != null ? "Restauração iniciada id="+r.getId() : "Falha ao iniciar restauração");
                } else if (opt.equals("5")) {
                    System.out.print("Id obra: "); String oid = scanner.nextLine().trim();
                    boolean ok = sys.removeObra(oid);
                    System.out.println(ok ? "Removida" : "Falha: obra ligada a exposição/restauração ou inexistente");
                } else if (opt.equals("6")) {
                    System.out.print("Id visitante: "); String vid = scanner.nextLine().trim();
                    boolean ok = sys.deleteVisitor(vid);
                    System.out.println(ok ? "Visitante removido" : "Falha ao remover visitante");
                } else if (opt.equals("7")) {
                    System.out.println("Obras:");
                    sys.getObrasMap().values().forEach(o -> System.out.println(o.toString()));
                    System.out.println("Exposições:");
                    sys.getExposicoesMap().values().forEach(e -> System.out.println(e.toString()));
                } else if (opt.equals("0")) break;
            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }

    private static void visitorMenu(Visitor v, MuseumSystem sys) {
        while (true) {
            System.out.println("\n--- Menu Visitante ---");
            System.out.println("1) Listar obras");
            System.out.println("2) Listar exposições");
            System.out.println("3) Inscrever-se em exposição");
            System.out.println("4) Excluir minha conta");
            System.out.println("0) Logout");
            System.out.print("> ");
            String opt = scanner.nextLine().trim();
            if (opt.equals("1")) {
                sys.listObras().forEach(o -> System.out.println(o.toString()));
            } else if (opt.equals("2")) {
                sys.listExposicoes().forEach(e -> System.out.println(e.toString()));
            } else if (opt.equals("3")) {
                System.out.print("Id exposicao: "); String eid = scanner.nextLine().trim();
                boolean ok = sys.subscribeVisitorToExposicao(v.getId(), eid);
                System.out.println(ok ? "Inscrição realizada" : "Falha (conflito de data ou já inscrito)");
            } else if (opt.equals("4")) {
                boolean ok = sys.visitorDeleteOwnAccount(v.getId());
                System.out.println(ok ? "Conta excluída" : "Falha ao excluir conta");
                if (ok) break;
            } else if (opt.equals("0")) break;
        }
    }
}
