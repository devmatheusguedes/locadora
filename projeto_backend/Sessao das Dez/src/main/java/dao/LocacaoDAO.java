package dao;

import model.LocacaoModel;
import model.MCliente;
import model.MItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocacaoDAO {
    public ArrayList<Map<String, Object>> listar(String nome) throws ExceptionDAO {
        String sql = "SELECT cliente.nome, cliente.id_cliente, item.titulo, item.tipo, item.preco, item.id_item, " +
                "data_aluguel, data_devolucao, status, id_locacao " +
                "FROM locacao " +
                "INNER JOIN cliente ON locacao.id_cliente = cliente.id_cliente " +
                "INNER JOIN item ON locacao.id_item = item.id_item " +
                "WHERE cliente.nome like '%"+nome+"%' order by cliente.nome;";
        ArrayList<Map<String, Object>> consulta = null;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;

        try{
            connection = new ConnectorMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            resultSet = pStatement.executeQuery();
            if (resultSet != null){
                consulta = new ArrayList<Map<String, Object>>();
                while (resultSet.next()){
                Map<String, Object> registro = new HashMap<>();
                registro.put("locacao_id", resultSet.getInt("id_locacao"));
                registro.put("cliente_id", resultSet.getInt("id_cliente"));
                registro.put("cliente_nome", resultSet.getString("nome"));
                registro.put("item_id", resultSet.getInt("id_item"));
                registro.put("item_titulo", resultSet.getString("titulo"));
                registro.put("item_tipo", resultSet.getString("tipo"));
                registro.put("item_preco", resultSet.getString("preco"));
                registro.put("locacao_dtlocacao", resultSet.getDate("data_aluguel"));
                registro.put("locacao_devolucao", resultSet.getDate("data_devolucao"));
                registro.put("locaao_status", resultSet.getString("status"));

                consulta.add(registro);
                }
            }
        }catch (SQLException exception){
            throw new ExceptionDAO("Erro ao listar items alugados." + exception);
        }finally {
            try{
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar conexão locação! " + exception);
            }

            try {
                if (pStatement != null){
                    pStatement.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar prepared statement locação! "+ exception);
            }
        }
        return consulta;
    }

    public void salvar(LocacaoModel locacaoModel) throws ExceptionDAO{
        String sql = "INSERT INTO locacao " +
                "(id_cliente, id_item, data_aluguel, data_devolucao, status)" +
                "VALUES(?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = new ConnectorMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, locacaoModel.getCliente().getCodCliente());
            pStatement.setInt(2, locacaoModel.getItem().getCod_item());
            pStatement.setDate(3, locacaoModel.getData_aluguel());
            pStatement.setDate(4, locacaoModel.getData_devolucao());
            pStatement.setString(5, locacaoModel.getStatus());
            pStatement.execute();
        }catch (SQLException exepcion){
            throw new ExceptionDAO("Erro ao cadastrar aluguel"+exepcion);
        }finally {
            try{
                if (pStatement != null){
                    pStatement.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar PreparedStatement"+exception);
            }

            try {
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar conexão: "+exception);
            }
        }
    }

    public void alterar(LocacaoModel locacaoModel) throws ExceptionDAO {
        String sql = "UPDATE locacao SET id_cliente = ?, id_item = ?, " +
                "data_aluguel = ?, data_devolucao = ?, status = ?" +
                " WHERE id_locacao = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = new ConnectorMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, locacaoModel.getCliente().getCodCliente());
            pStatement.setInt(2, locacaoModel.getItem().getCod_item());
            pStatement.setDate(3, locacaoModel.getData_aluguel());
            pStatement.setDate(4, locacaoModel.getData_devolucao());
            pStatement.setString(5, locacaoModel.getStatus());
            pStatement.setInt(6, locacaoModel.getId_locacao());
            pStatement.execute();
        }catch (SQLException exception){
            throw new ExceptionDAO("Erro ao alterar casdastro da locação! "+exception);
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar conexão no Dao Locação!" + exception);
            }

            try {
                if (pStatement != null){
                    pStatement.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar Pstatemente na Dao locação! "+exception);
            }


            }


    }

    public void apagar(Integer id_locacao) throws ExceptionDAO {
        String sql = "DELETE FROM locacao WHERE id_locacao = ?";
        Connection connection = null;
        PreparedStatement pStatemente = null;

        try {
            connection = new ConnectorMVC().getConnection();
            pStatemente = connection.prepareStatement(sql);
            pStatemente.setInt(1, id_locacao);
            pStatemente.execute();
        }catch (SQLException exception){
            throw new ExceptionDAO("Erro ao apagar resgistro de locacao: "+exception);
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar conexão locacao: "+exception);
            }

            try {
                if (pStatemente != null){
                    pStatemente.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar PreparedStatement locacao: "+exception);
            }
        }
    }
}
