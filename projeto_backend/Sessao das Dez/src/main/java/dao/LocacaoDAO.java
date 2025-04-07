package dao;

import model.LocacaoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocacaoDAO {

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
}
