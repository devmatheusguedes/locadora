package controller;

import dao.ExceptionDAO;
import model.MAtor;
import model.MCliente;
import model.MFilme;

import java.sql.Date;
import java.util.ArrayList;



public class ClienteController {

    public boolean castroCliente(String nome, String cpf, String endereco, java.sql.Date dataNascimento, String email) throws ExceptionDAO {

        if (nome != null && nome.length() > 0 && cpf != null && cpf.length() > 0 && endereco != null
        && endereco.length() > 0 && dataNascimento != null && email != null
                && email.length() > 0 && validarCpf(cpf) ){

            MCliente cliente = new MCliente(nome, cpf, endereco, dataNascimento, email);
            cliente.cadastroCliente(cliente);
            return true;
        }
        return false;
    }

    public ArrayList<MCliente> listarClientes(String nome) throws ExceptionDAO{
        return new MCliente().listarClientes(nome);
    }

    //String nome, String cpf, String endereco, Date dataNascimento, String email
    public boolean alterar(Integer cod_cliente, String nome, String cpf, String endereco,
                           Date dataNascimento, String email) throws ExceptionDAO {

        if(nome != null && nome.length() > 0 && cpf != null && cpf.length() > 0 && endereco != null
                && endereco.length() > 0 && dataNascimento != null && email != null
                && email.length() > 0 && validarCpf(cpf) ){
            MCliente cliente = new MCliente(nome, cpf, endereco, dataNascimento, email);
            cliente.setCodCliente(cod_cliente);
            cliente.alterar(cliente);
            return true;
        }

        return false;
    }



    public boolean validarCpf(String cpf){
        for (int i = 0; i < cpf.length(); i++){
            if(!Character.isDigit(cpf.charAt(i))){
                if(!(i ==3 || i ==7 || i ==11)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validarData(String dataNascimento){
        for (int i = 0; i < dataNascimento.length(); i++){
            if(!Character.isDigit(dataNascimento.charAt(i))){
                if(!(i ==3 || i ==5)){
                    return false;
                }
                // aprenda a transformar um date em string. É mais pratico.
            }
        }
        return true;
    }

    public boolean apagarCliente(Integer cod_cliente) throws ExceptionDAO{
        if (cod_cliente != 0){
            MCliente cliente = new MCliente();
            cliente.setCodCliente(cod_cliente);
            cliente.apagarCliente(cliente);
            return true;
        }
        return false;
    }

}
