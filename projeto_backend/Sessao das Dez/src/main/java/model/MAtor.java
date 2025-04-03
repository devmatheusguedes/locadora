package model;

import dao.AtorDAO;
import dao.ExceptionDAO;

import java.util.ArrayList;

import java.util.ArrayList;

public class MAtor {
private Integer codAtor;
    private String nome;
    private String nacionalidade;
    private MFilme filme;

    public MAtor(){
        MFilme filme = new MFilme();
        this.setFilmes(filme);
    }

    public MAtor(String nome, String nacionalidade, Integer id_filme){
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        MFilme filme1 = new MFilme();
        filme1.setCodFilme(id_filme);
        this.setFilmes(filme1);
    }

    public void alterar(MAtor ator) throws ExceptionDAO{
        new AtorDAO().alterar(ator);
    }

    public void deletarAtor(MAtor ator) throws ExceptionDAO{
        new AtorDAO().deletarAtor(ator);
    }

    public Integer getCodAtor() {
        return codAtor;
    }

    public void setCodAtor(Integer codAtor) {
        this.codAtor = codAtor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public MFilme getFilmes() {
        return filme;
    }

    public void setFilmes(MFilme filme) {
        this.filme = filme;
    }

    public void cadastrarAtor(MAtor ator) throws ExceptionDAO {
        new AtorDAO().cadastrarAtor(ator);
    }

    public ArrayList<MAtor> listarAtores(String nome) throws ExceptionDAO {
        return new AtorDAO().listarAtores(nome);
    }

}

