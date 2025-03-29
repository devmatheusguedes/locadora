package model;

import dao.ClienteDAO;
import dao.ExceptionDAO;

import java.sql.Date;
import java.util.ArrayList;


public class MCliente {
    private Integer codCliente;
    private String nome;
    private String cpf;
    private String endereco;
    private Date dataNascimento;
    private String email;
    private ArrayList<MItem> itens = new ArrayList<MItem>();

    public MCliente(){

    }

    public MCliente(String nome, String cpf, String endereco, Date dataNascimento, String email){
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    public void cadastroCliente(MCliente cliente) throws ExceptionDAO {
        new ClienteDAO().cadastrarCliente(cliente);
    }

    public ArrayList<MCliente> listarClientes(String nome) throws ExceptionDAO{
        return new ClienteDAO().listarClientes(nome);
    }

    public void alterar(MCliente cliente) throws ExceptionDAO{
        new ClienteDAO().alterar(cliente);
    }




    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<MItem> getItens() {
        return itens;
    }

    public void setItens(ArrayList<MItem> itens) {
        this.itens = itens;
    }

    public void apagarCliente(MCliente cliente) throws ExceptionDAO{
        new ClienteDAO().apagarCliente(cliente);
    }

}
