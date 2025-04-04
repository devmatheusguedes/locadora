package controller;

import dao.ExceptionDAO;
import model.MAtor;
import model.MFilme;

import java.util.ArrayList;

public class AtorController {
    public boolean cadastroAtor(String nacionalidade, String nome, Integer id_filme) throws ExceptionDAO {
        if (nacionalidade != null && nacionalidade.length() > 0 && nome != null && nome.length() > 0 && id_filme != 0){
            MFilme filme = new MFilme();
            filme.setCodFilme(id_filme);
            MAtor ator = new MAtor(nome, nacionalidade, id_filme);
            ator.cadastrarAtor(ator);
            return true;
        }
        return false;
    }

    public ArrayList<MAtor> listarAtores(String nome) throws ExceptionDAO {
        return new MAtor().listarAtores(nome);
    }

    public boolean alterar(Integer cod_ator, String nome, String nacionalidade, Integer id_filme) throws ExceptionDAO {
        if (nacionalidade != null && nacionalidade.length()>0 && nome != null && nome.length()>0 && id_filme != 0){
            MAtor ator = new MAtor(nome, nacionalidade, id_filme);
            ator.setCodAtor(cod_ator);
            ator.alterar(ator);
            return true;
        }
        return false;
    }

    public boolean deletarAtor(Integer cod_ator) throws ExceptionDAO{
        if(cod_ator != 0){
            MAtor ator = new MAtor();
            ator.setCodAtor(cod_ator);
            ator.deletarAtor(ator);
            return true;
        }

        return false;
    }


}
