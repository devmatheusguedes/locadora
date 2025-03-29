package model;

import dao.AtorDAO;
import dao.ExceptionDAO;

import java.util.ArrayList;

import java.util.ArrayList;

public class MAtor {
private Integer codAtor;
    private String nome;
    private String nacionalidade;
    private ArrayList<MFilme> filmes = new ArrayList<MFilme>();

    public MAtor(){

    }

    public MAtor(String nome, String nacionalidade){
        this.nome = nome;
        this.nacionalidade = nacionalidade;
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

    public ArrayList<MFilme> getFilmes() {
        return filmes;
    }

    public void setFilmes(ArrayList<MFilme> filmes) {
        this.filmes = filmes;
    }

    public void cadastrpAtor(MAtor ator) throws ExceptionDAO {
        new AtorDAO().cadastrarAtor(ator);
    }

    public ArrayList<MAtor> listarAtores(String nome) throws ExceptionDAO {
        return new AtorDAO().listarAtores(nome);
    }

}

