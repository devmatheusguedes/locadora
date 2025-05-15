package controller;

import dao.ExceptionDAO;
import dao.LocacaoDAO;
import model.LocacaoModel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

public class LocacaoController{
    public boolean salvar(Integer id_cliente, Integer id_item, Date data_aluguel, Date data_devolucao, String status)throws
            ExceptionDAO {
        if (id_cliente != 0 && id_item != 0 && status.length() > 0){
        LocacaoModel locacaoModel = new LocacaoModel(id_cliente, id_item, data_aluguel, data_devolucao, status);
        locacaoModel.salvar(locacaoModel);
        return true;
        }
        return false;
    }

    public boolean alterar(Integer id_locacao, Integer id_cliente, Integer id_item, Date data_aluguel, Date data_devolucao, String status) throws ExceptionDAO{
        if (id_cliente != 0 && id_item != 0 && status.length() > 0){
            LocacaoModel locacaoModel = new LocacaoModel(id_cliente, id_item, data_aluguel, data_devolucao, status);
            locacaoModel.setId_locacao(id_locacao);
            locacaoModel.alterar(locacaoModel);
            return true;
        }
        return false;
    }

    public ArrayList<Map<String, Object>> listar(String nome_cliente) throws ExceptionDAO{
       return new LocacaoModel().listar(nome_cliente);
    }

    public boolean apagar(Integer id_locacao) throws ExceptionDAO{
        if (id_locacao != 0){
            LocacaoModel locacaoModel = new LocacaoModel();
            locacaoModel.apagar(id_locacao);
            return true;
        }
        return false;
    }
}
