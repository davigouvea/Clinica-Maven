package br.com.clinica.model;

public class Tutor {

    private String nome;
    private String cpf;
    private String telefone;

    // Construtor
    public Tutor(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    // 2. Métodos Getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }
}