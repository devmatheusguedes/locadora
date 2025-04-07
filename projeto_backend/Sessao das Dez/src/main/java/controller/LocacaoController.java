package controller;

import dao.ExceptionDAO;
import model.LocacaoModel;

import java.sql.Date;

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
}
