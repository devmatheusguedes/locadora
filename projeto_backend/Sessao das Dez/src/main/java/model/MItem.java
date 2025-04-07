package model;
import dao.ClienteDAO;
import dao.ExceptionDAO;
import dao.ItemDAO;

import java.sql.Date;
import java.util.ArrayList;

public class MItem {
    private Integer cod_item;
    private double preco;
    private String tipo;
    private MFilme filme;
    private ClienteDAO cliente;




    public MItem(){}
    public MItem(Integer cod_filme, String tipo, double preco, String titulo){
        this.preco = preco;
        this.tipo = tipo;
        MFilme filme = new MFilme();
        filme.setCodFilme(cod_filme);
        filme.setTitulo(titulo);
        this.setFilme(filme);
    }

    public Integer getCod_item() {
        return cod_item;
    }


    public void setCod_item(Integer cod_item) {
        this.cod_item = cod_item;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



    public MFilme getFilme() {
        return filme;
    }

    public void setFilme(MFilme filme) {
        this.filme = filme;
    }

    public ClienteDAO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDAO cliente) {
        this.cliente = cliente;
    }

    public void salvar(MItem item) throws ExceptionDAO {
        new ItemDAO().salvar(item);
    }
    public ArrayList<MItem> listarItens(String titulo)throws ExceptionDAO{
        return new ItemDAO().listarItems(titulo);
    }

    public void alterar(MItem item)throws ExceptionDAO{
        new ItemDAO().alterar(item);
    }

    public void apagar(int cod_item)throws ExceptionDAO{
         new ItemDAO().apagar(cod_item);
    }

}
