package dao;

import model.MCliente;

import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class ClienteDAO {
    public void cadastrarCliente(MCliente cliente) throws ExceptionDAO{
        String sql = "INSERT INTO public.cliente("+
                "cpf, nome, endereco, data_de_nascimento, email)" +
                "VALUES (?, ?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = new ConnectorMVC().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getCpf());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getEndereco());
            preparedStatement.setDate(4, (Date) cliente.getDataNascimento());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.execute();
        }catch (SQLException e){
            throw new ExceptionDAO("Erro ao cadastrar cliente: " + e);

        }finally {
            try {
                if(preparedStatement != null){
                    preparedStatement.close();
                };
            }catch (SQLException e){
                throw new ExceptionDAO("Erro ao fechar o statement: "+e);
            }

            try {
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                throw new ExceptionDAO("Erro ao fechar conexão: "+e);
            }
        }
    }

    public ArrayList<MCliente> listarClientes(String nome) throws ExceptionDAO{
        String sql = "select * from cliente where nome like '%"+nome+"%' order by nome";
        ArrayList<MCliente> clientes = null;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet Rs = null;

        try{
            connection = new ConnectorMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            Rs = pStatement.executeQuery();

            if(Rs != null){
                clientes = new ArrayList<MCliente>();

                while (Rs.next()){
                    MCliente cliente = new MCliente();
                    cliente.setNome(Rs.getString("nome"));
                    cliente.setCodCliente(Rs.getInt("id_cliente"));
                    cliente.setCpf(Rs.getString("cpf"));
                    cliente.setEndereco(Rs.getString("endereco"));
                    cliente.setEmail(Rs.getString("email"));
                    cliente.setDataNascimento(Rs.getDate("data_de_nascimento"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("erro ao consultar clientes "+e);
        } finally {
            try {
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e) {
                throw new ExceptionDAO("erro ao fechar conexão"+e);
            }

            try {
                if (pStatement!= null){
                    pStatement.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("erro ao fechar Pstatment. "+exception);
            }
        }

        return clientes;
    }

    public void alterar(MCliente cliente) throws ExceptionDAO {
        //String nome, String cpf, String endereco, Date dataNascimento, String email
        String sql = "update cliente set nome = ?, cpf = ?, endereco = ?, data_de_nascimento = ?," +
                " email = ? where cod_cliente = ?";
        Connection conexao = null;
        PreparedStatement pStatement = null;

        try{
            conexao = new ConnectorMVC().getConnection();
            pStatement = conexao.prepareStatement(sql);
            pStatement.setString(1, cliente.getNome());
            pStatement.setString(2, cliente.getCpf());
            pStatement.setString(3, cliente.getEndereco());
            pStatement.setDate(4, cliente.getDataNascimento());
            pStatement.setString(5, cliente.getEmail());
            pStatement.setInt(6, cliente.getCodCliente());
            pStatement.execute();
        }catch (SQLException exception){
            throw new ExceptionDAO("Erro ao alterar cliente "+exception);
        }finally {
            try{
                if(conexao != null) conexao.close();
            }catch (SQLException exception) {
                throw new ExceptionDAO("Erro ao fechar conexão");
            }
            try{
                if(pStatement != null) pStatement.close();
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar PreparedStatement "+exception);
            }
            }
    }

    public void apagarCliente(MCliente cliente) throws ExceptionDAO{
        String sql = "delete from cliente where cod_cliente = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = new ConnectorMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, cliente.getCodCliente());
            pStatement.execute();
        }catch (SQLException exception){
            throw new ExceptionDAO("Erro ao apagar cliente" + exception);
        }finally {
            try {
                if (connection != null) connection.close();
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar conexão de cliente" + exception);
            }
            try {
                if(pStatement != null) pStatement.close();
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar PreparedStatement de cliente"+exception);
            }
        }
    }
}
