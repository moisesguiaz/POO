package archive;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class GerenciadorDeArquivos {
    private static final String DIR = "data/";
    private static final String ARQUIVO_OBRAS = DIR + "obras.txt";
    private static final String ARQUIVO_EXPOSICOES = DIR + "exposicoes.txt";
    private static final String ARQUIVO_VISITANTES = DIR + "visitantes.txt";

    public GerenciadorDeArquivos() {
        File directory = new File(DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void salvarObras(List<Obra> obras) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_OBRAS))) {
            for (Obra obra : obras) {
                bw.write(obra.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar obras: " + e.getMessage());
        }
    }

    public List<Obra> carregarObras() {
        List<Obra> obras = new ArrayList<>();
        File file = new File(ARQUIVO_OBRAS);
        if (!file.exists()) return obras;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Obra obra = Obra.fromCSV(line);
                if (obra != null) obras.add(obra);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar obras: " + e.getMessage());
        }
        return obras;
    }

    public void salvarExposicoes(List<Exposicao> exposicoes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_EXPOSICOES))) {
            for (Exposicao exposicao : exposicoes) {
                bw.write(exposicao.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar exposições: " + e.getMessage());
        }
    }

    public List<Exposicao> carregarExposicoes() {
        List<Exposicao> exposicoes = new ArrayList<>();
        File file = new File(ARQUIVO_EXPOSICOES);
        if (!file.exists()) return exposicoes;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Exposicao exposicao = Exposicao.fromCSV(line);
                if (exposicao != null) exposicoes.add(exposicao);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar exposições: " + e.getMessage());
        }
        return exposicoes;
    }

    public void salvarVisitantes(List<Visitante> visitantes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_VISITANTES))) {
            for (Visitante visitante : visitantes) {
                bw.write(visitante.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar visitantes: " + e.getMessage());
        }
    }

    public List<Visitante> carregarVisitantes() {
        List<Visitante> visitantes = new ArrayList<>();
        File file = new File(ARQUIVO_VISITANTES);
        if (!file.exists()) return visitantes;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Visitante visitante = Visitante.fromCSV(line);
                if (visitante != null) visitantes.add(visitante);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar visitantes: " + e.getMessage());
        }
        return visitantes;
    }
}
