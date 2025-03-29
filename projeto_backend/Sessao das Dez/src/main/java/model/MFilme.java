package model;

import dao.ExceptionDAO;
import dao.FilmeDAO;

import java.util.ArrayList;

public class MFilme {
    private Integer codFilme;
    private String titulo;
    private String genero;
    private String sinopse;
    private Integer duracao;
    private ArrayList<MItem> itens = new ArrayList<MItem>();
    private ArrayList<MAtor> atores = new ArrayList<MAtor>();

    public MFilme(){

    }

    public MFilme(String titulo, String genero, String sinopse, Integer duracao){
        this.titulo = titulo;
        this.genero = genero;
        this.sinopse = sinopse;
        this.duracao = duracao;
    }
    public void cadastrarFilme(MFilme filme) throws ExceptionDAO {
        new FilmeDAO().cadastrarFilme(filme);
    }

    public ArrayList<MFilme> listarFilmes(String nome) throws ExceptionDAO{
        return new FilmeDAO().listarFilmes(nome);
    }

    public void alterar(MFilme filme) throws ExceptionDAO{
        new FilmeDAO().alterar(filme);
    }

    public void apagar(MFilme filme) throws ExceptionDAO{
        new FilmeDAO().apagar(filme);
    }

    public Integer getCodFilme() {
        return codFilme;
    }

    public void setCodFilme(Integer codFilme) {
        this.codFilme = codFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public ArrayList<MItem> getItens() {
        return itens;
    }

    public void setItens(ArrayList<MItem> itens) {
        this.itens = itens;
    }

    public ArrayList<MAtor> getAtores() {
        return atores;
    }

    public void setAtores(ArrayList<MAtor> atores) {
        this.atores = atores;
    }



}
