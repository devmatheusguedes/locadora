package controller;

import dao.ExceptionDAO;
import model.MFilme;

import java.util.ArrayList;

public class FilmeController {

    public boolean cadastrarFilme(String titulo, String genero, String sinopse, Integer duracao) throws ExceptionDAO {
        if (titulo != null && titulo.length() >0 && genero != null && genero.length()>0 && sinopse != null && sinopse.length() >0){
            MFilme filme = new MFilme(titulo, genero, sinopse, duracao);
            filme.cadastrarFilme(filme);
            return true;
        }
        return false;
    }

    public ArrayList<MFilme> listarFilme(String nome) throws ExceptionDAO{
        return new MFilme().listarFilmes(nome);
    }

    public boolean alterar(Integer cod_filme, String titulo, String genero, String sinopse, Integer duracao) throws ExceptionDAO{
        if(titulo.length() >0 && titulo != null && genero != null && genero.length()>0 && sinopse!=null && sinopse.length()>0 && duracao!=null){
            MFilme filme = new MFilme(titulo, genero, sinopse, duracao);
            filme.setCodFilme(cod_filme);
            filme.alterar(filme);

            return true;

        }
        return false;
    }

    public boolean apagar(Integer cod_filme) throws ExceptionDAO{
        if (cod_filme != 0){
            MFilme filme = new MFilme();
            filme.setCodFilme(cod_filme);
            filme.apagar(filme);
            return true;
        }
        return false;
    }

}
