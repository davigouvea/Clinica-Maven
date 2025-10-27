package br.com.clinica.repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Importa as classes do Gson que adicionamos no pom.xml
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

// Importa o modelo Tutor
import br.com.clinica.model.Tutor;

public class TutorRepository {
    private static final String TUTORES_FILE_PATH = "tutores.json";

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Map<String, Tutor> tutores;

    public TutorRepository() {
        carregarDados();
    }

    private void carregarDados() {
        try (Reader reader = new FileReader(TUTORES_FILE_PATH)) {
            Type tipoMapa = new TypeToken<HashMap<String, Tutor>>(){}.getType();
            
            // Converte o JSON do arquivo de volta para o Mapa
            tutores = gson.fromJson(reader, tipoMapa);
            System.out.println("Dados dos tutores carregados com sucesso.");

        } catch (IOException e) {
            System.err.println("Arquivo " + TUTORES_FILE_PATH + " não encontrado, iniciando com dados vazios.");
        }
        
        if (tutores == null) {
            tutores = new HashMap<>();
        }
    }

    public void salvarDados() {
        try (Writer writer = new FileWriter(TUTORES_FILE_PATH)) {
            // Converte o mapa de tutores para o formato JSON e escreve no arquivo
            gson.toJson(tutores, writer);
            System.out.println("Dados dos tutores salvos com sucesso.");

        } catch (IOException e) {
            System.err.println("Erro ao salvar dados dos tutores: " + e.getMessage());
        }
    }

    // --- Métodos de acesso aos dados (CRUD) ---

    public void salvar(Tutor tutor) {
        tutores.put(tutor.getCpf(), tutor);
    }

    public Tutor buscarPorCpf(String cpf) {
        return tutores.get(cpf);
    }

    public List<Tutor> listarTodos() {
        return new ArrayList<>(tutores.values());
    }
}