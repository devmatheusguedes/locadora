package dao;

import model.MFilme;
import model.MItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("ThrowFromFinallyBlock")
public class ItemDAO {

    public void salvar(MItem item) throws ExceptionDAO {
        String sql = "INSERT INTO item" +
                "(cod_filme, titulo, preco, tipo_de_midia) " +
                "VALUES (?,?,?, ?)";
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = new ConnectorMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, item.getFilme().getCodFilme());
            pStatement.setString(4, item.getFilme().getTitulo());
            pStatement.setDouble(2, item.getPreco());
            pStatement.setString(3, item.getTipo());
            pStatement.execute();
        }catch (SQLException exception){
            throw new ExceptionDAO("Erro ao cadastar item: "+exception);
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar conexão: "+exception);
            }
            try {
                if(pStatement!=null){
                    pStatement.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar pStatement:"+ exception);
            }
        }
    }

    public ArrayList<MItem> listarItems(String titulo) throws ExceptionDAO {

        String sql = "SELECT it.cod_item, it.preco, it.tipo_de_midia, fi.cod_filme, fi.titulo\n" +
                "FROM public.item it\n" +
                "INNER JOIN public.filme fi ON it.cod_filme = fi.cod_filme\n" +
                "WHERE fi.titulo LIKE '"+titulo+"%'\n" +
                "ORDER BY fi.titulo;";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<MItem> itens = null;

        try {
            connection = new ConnectorMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rS = pStatement.executeQuery();
            if(rS != null){
                itens = new ArrayList<MItem>();
                while (rS.next()){
                    MItem item = new MItem();
                    MFilme filme = new MFilme();
                    item.setCod_item(rS.getInt("cod_item"));
                    filme.setCodFilme(rS.getInt("cod_filme"));
                    filme.setTitulo(rS.getString("titulo"));
                    item.setFilme(filme);
                    item.setTipo(rS.getString("tipo_de_midia"));
                    item.setPreco(rS.getDouble("preco"));
                    itens.add(item);
                    System.out.println(itens);
                }
            }
        }catch (SQLException exception){
            throw new ExceptionDAO("Erro ao listar items: "+exception);
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar conexão: "+exception);
            }

            try {
                if(pStatement!=null){
                    pStatement.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar pStatement: "+exception);
            }
        }

        return itens;

    }

    public void alterar(MItem item)throws ExceptionDAO{
        String sql = "update item set cod_filme = ?, preco = ?, tipo_de_midia = ? where cod_item = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = new ConnectorMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, item.getFilme().getCodFilme());
            pStatement.setDouble(2, item.getPreco());
            pStatement.setString(3, item.getTipo());
            pStatement.setInt(4, item.getCod_item());
            pStatement.execute();
        }catch (SQLException exception){
            throw new ExceptionDAO("Erro ao alterar item: "+exception);
        }finally {
            try {
                if (connection!=null){
                    connection.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar conexão "+exception);
            }
            try {
                if(pStatement!= null){
                    pStatement.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar pStatement: "+exception);
            }
        }

    }


    public void apagar(int cod_item) throws ExceptionDAO {
        String sql = "delete from item where cod_item = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = new ConnectorMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, cod_item);
            pStatement.execute();
        }catch (SQLException exception){
            throw new ExceptionDAO("Erro ao apagar item: "+exception);
        }finally {
            try {
                if (connection!=null){
                    connection.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar conexão: "+exception);
            }

            try {
                if(pStatement!=null){
                    pStatement.close();
                }
            }catch (SQLException exception){
                throw new ExceptionDAO("Erro ao fechar pStatement: "+exception);
            }
        }
    }
}
