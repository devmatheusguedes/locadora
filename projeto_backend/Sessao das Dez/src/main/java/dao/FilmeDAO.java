package dao;
import model.MCliente;
import model.MFilme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("ThrowFromFinallyBlock")
public class FilmeDAO {
    public void cadastrarFilme(MFilme filme) throws ExceptionDAO{
        String sql = "INSERT INTO public.filme(" +
                "titulo, genero, sinopse, duracao)" +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection connection = null;

        try{
            connection = new ConnectorMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, filme.getTitulo());
            pStatement.setString(2, filme.getGenero());
            pStatement.setString(3, filme.getSinopse());
            pStatement.setInt(4, filme.getDuracao());
            pStatement.execute();
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao cadastrar o filme: "+e);
        }finally {
            try {
                if(pStatement != null){
                    pStatement.close();
                }
            }catch (SQLException e){
                throw new ExceptionDAO("Erro ao fechar a Statement: "+e);
            }
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                throw new ExceptionDAO("Erro ao fechar a conexão connectionn: "+e);
            }
        }
    }

    public void alterar(MFilme filme) throws ExceptionDAO {
        // usar o update (DML) para alterar o cadastro
        String sql = "update filme set titulo = ?, genero = ?, sinopse = ?, duracao = ? where cod_filme = ?";
        Connection conexao = null;
        PreparedStatement pStatement = null;
        try {
            conexao = new ConnectorMVC().getConnection();
            pStatement = conexao.prepareStatement(sql);
            pStatement.setString(1, filme.getTitulo());
            pStatement.setString(2, filme.getGenero());
            pStatement.setString(3, filme.getSinopse());
            pStatement.setInt(4, filme.getDuracao());
            pStatement.setInt(5, filme.getCodFilme());
            pStatement.execute();
        }catch (SQLException exception){
            throw new ExceptionDAO("ERRO ao alterar dados do sistema "+ exception);
        }finally {
            try {
                if(conexao != null){
                    conexao.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("ERRO ao fechar conexão "+exception);
            }
            try {
                if(pStatement!=null){
                    pStatement.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("ERRO ao fechar o pStatement "+exception);
            }
        }
    }

    public ArrayList<MFilme> listarFilmes(String nome) throws ExceptionDAO {
        String sql = "select * from filme where titulo like '%"+nome+"%' order by titulo";

        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<MFilme> filmes = null;

        try {
            connection = new ConnectorMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();

            if (rs != null){
                filmes = new ArrayList<MFilme>();

                while (rs.next()){
                    MFilme filme = new MFilme();
                    filme.setCodFilme(rs.getInt("cod_filme"));
                    filme.setTitulo(rs.getString("titulo"));
                    filme.setDuracao(rs.getInt("duracao"));
                    filme.setSinopse(rs.getString("sinopse"));
                    filme.setGenero(rs.getString("genero"));
                    filmes.add(filme);
                }
            }
        }catch (SQLException e){
            throw new ExceptionDAO("erro ao consultar filme" + e);
        }
        finally {
            try {
                if(pStatement !=null){
                    pStatement.close();
                }
            }catch (SQLException e){
                throw new ExceptionDAO("erro ao fechar a statement: "+e);
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                throw new ExceptionDAO("erro ao fechar conexão: "+e);
            }
        }
        return filmes;
    }

    public void apagar(MFilme filme) throws ExceptionDAO {
        String sql = "delete from filme where cod_filme = ?";
        Connection conexao = null;
        PreparedStatement pStatement = null;
        try{
            conexao = new ConnectorMVC().getConnection();
            pStatement = conexao.prepareStatement(sql);
            pStatement.setInt(1, filme.getCodFilme());
            pStatement.execute();
        }catch (SQLException e){
            throw new ExceptionDAO("Erro ao deletar filme "+ e);
        }finally {
            try{
                if(conexao!=null){
                    conexao.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fachar conexão "+exception);
            }

            try {
                if(pStatement!= null){
                    pStatement.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar o PreparedStatement "+exception);
            }
        }
    }
}