package model;

import dao.ExceptionDAO;
import dao.LocacaoDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

public class LocacaoModel {
    private Integer id_locacao;
    private MItem item;
    private MCliente cliente;
    private String status;
    private Date data_aluguel;
    private Date data_devolucao;

    public LocacaoModel(){

    }
    public LocacaoModel(Integer id_cliente, Integer id_intem, Date data_aluguel, Date data_devolucao, String status){
        MItem item1 = new MItem();
        item1.setCod_item(id_intem);
        MCliente cliente1 = new MCliente();
        cliente1.setCodCliente(id_cliente);
        this.setItem(item1);
        this.setCliente(cliente1);
        this.setData_aluguel(data_aluguel);
        this.setData_devolucao(data_devolucao);
        this.setStatus(status);
    }
    public void salvar(LocacaoModel locacaoModel)throws ExceptionDAO {
        new LocacaoDAO().salvar(locacaoModel);
    }

    public void alterar(LocacaoModel locacaoModel) throws ExceptionDAO{
        new LocacaoDAO().alterar(locacaoModel);
    }

   public ArrayList<Map<String, Object>> listar(String nome_cliente)throws ExceptionDAO{
        return new LocacaoDAO().listar(nome_cliente);
   }

    public void apagar(Integer id_locacao) throws ExceptionDAO{
        new LocacaoDAO().apagar(id_locacao);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData_aluguel() {
        return data_aluguel;
    }

    public void setData_aluguel(Date data_aluguel) {
        this.data_aluguel = data_aluguel;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public MItem getItem() {
        return item;
    }

    public void setItem(MItem item) {
        this.item = item;
    }

    public MCliente getCliente() {
        return cliente;
    }

    public void setCliente(MCliente cliente) {
        this.cliente = cliente;
    }

    public Integer getId_locacao() {
        return id_locacao;
    }

    public void setId_locacao(Integer id_locacao) {
        this.id_locacao = id_locacao;
    }
}
