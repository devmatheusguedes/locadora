package controller;

import dao.ClienteDAO;
import dao.ExceptionDAO;
import dao.FilmeDAO;
import model.MFilme;
import model.MItem;

import java.sql.Date;
import java.util.ArrayList;

public class ItemController {
public boolean salvar(Integer id_filme, String tipo, double preco, String titulo)throws ExceptionDAO {
    if(id_filme>0 && !tipo.isEmpty()&& preco>0){
        MItem item = new MItem(id_filme, tipo, preco, titulo);
        item.salvar(item);
        return true;
    }else {
        return false;
    }
}
public ArrayList<MItem> listarItens(String titulo) throws ExceptionDAO{
    return new MItem().listarItens(titulo);
}

public boolean alterar(Integer cod_item, String tipo, String titulo, double preco, Integer cod_filme) throws ExceptionDAO{
    if((!tipo.isEmpty()) && (!titulo.isEmpty()) && (preco > 0) && cod_item!=null){
        MItem item = new MItem();
        MFilme filme = new MFilme();
        filme.setTitulo(titulo);
        filme.setCodFilme(cod_filme);
        item.setFilme(filme);
        item.setCod_item(cod_item);
        item.setPreco(preco);
        item.setTipo(tipo);

        item.alterar(item);

        return true;
    }
    return false;
}

public boolean apagar(int cod_item)throws ExceptionDAO{
    if (cod_item != 0) {
        new MItem().apagar(cod_item);
        return true;
    }
    return false;
}

}


