package com.gentebonita.gentebonita.gentebonita.models;

/**
 * Created by Vinicius on 29/06/2017.
 */

public class Perfil {
    private Integer id;
    private String nome;
    private String idade;
    private String imagem;
    private Float nota;

    public Perfil() {

    }

    public Perfil(Integer id, String nome, String idade, String imagem, Float nota) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.imagem = imagem;
        this.nota = nota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }
}
