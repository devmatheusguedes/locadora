package dao;

import model.MAtor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("ThrowFromFinallyBlock")
public class AtorDAO {
    public void cadastrarAtor(MAtor ator) throws ExceptionDAO{
        String sql = "INSERT INTO public.ator(nome, nacionalidade, id_filme)VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ConnectorMVC().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ator.getNome());
            preparedStatement.setString(2, ator.getNacionalidade());
            preparedStatement.setInt(3, ator.getFilmes().getCodFilme());

            preparedStatement.execute();
        }catch (SQLException e){
            throw new ExceptionDAO("ERRO ao cadastrar ator: "+ e);
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                throw new ExceptionDAO("ERRO ao fechar a conexão: "+e);
            }

            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
            }catch (SQLException e){
                throw new ExceptionDAO("ERRO ao fechar o Statement: "+e);
            }
        }

    }

    public ArrayList<MAtor> listarAtores(String nome) throws ExceptionDAO {
        String sql = "select * from ator where nome like '%"+nome+"%' order by nome";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<MAtor> atores = null;

        try {
            connection = new ConnectorMVC().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs != null){
                atores = new ArrayList<MAtor>();
                while (rs.next()){
                    //cod_ator, nome, nacionalidade
                    MAtor ator = new MAtor();
                    ator.setCodAtor(rs.getInt("id_ator"));
                    ator.setNacionalidade(rs.getString("nacionalidade"));
                    ator.setNome(rs.getString("nome"));
                    ator.getFilmes().setCodFilme(rs.getInt("id_filme"));

                    atores.add(ator);
                }
            }
        }catch (SQLException exception){
            throw new ExceptionDAO("Erro ao consultar atores no BD" +exception);
        }finally {
            try {
                if(preparedStatement != null){
                    preparedStatement.close();
                }
            }catch (SQLException exception){
               throw new ExceptionDAO( "erro ao fechar o preparedStatement"+exception);

            }

            try {

                if (connection != null){
                    connection.close();
                }

            }catch (SQLException exception){
                exception.printStackTrace();
                throw new ExceptionDAO("erro ao fechar conexão ");

            }
        }
        return atores;
    }

    @SuppressWarnings("ThrowFromFinallyBlock")
    public void alterar(MAtor ator) throws ExceptionDAO{
        String sql = "update ator set nome = ?, nacionalidade = ? where id_ator = ?";
        Connection conexao = null;
        PreparedStatement pstatement = null;

        try {
            conexao = new ConnectorMVC().getConnection();
            pstatement = conexao.prepareStatement(sql);
            pstatement.setString(1, ator.getNome());
            pstatement.setString(2, ator.getNacionalidade());
            pstatement.setInt(3, ator.getCodAtor());
            pstatement.execute();
        }catch (SQLException exception){
            throw new ExceptionDAO("ERRO ao alterar ator "+exception);
        }finally {
            try {
                if(conexao != null){
                    conexao.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("ERRO ao fechar conexão"+ exception);
            }

            try {
                if(pstatement != null){
                    pstatement.close();
                }
            }catch (SQLException exception){

                throw new ExceptionDAO("ERRO ao fechar preparedStatement "+exception);

            }
        }
    }

    public void deletarAtor(MAtor ator) throws ExceptionDAO{
        String sql = "delete from ator where id_ator = ?";
        Connection conexao = null;
        PreparedStatement pStatement = null;

        try {
            conexao = new ConnectorMVC().getConnection();
            pStatement = conexao.prepareStatement(sql);
            pStatement.setInt(1, ator.getCodAtor());
            pStatement.execute();
        }catch (SQLException exception){
            throw new ExceptionDAO("Erro ao deletar ator "+exception);
        }finally {
            try {
                if(conexao!=null) conexao.close();
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar conexão " + exception);
            }
        }

        try {
            if(pStatement!=null) pStatement.close();
        }catch (SQLException exception) {
            throw new ExceptionDAO("Erro ao fechar pStatement " + exception);
        }
    }
}
