package cenaflix;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class filmeDAO {
    public static boolean cadastrar(filmes f){
        try{
            conexaoJDBC conexao = new conexaoJDBC();
            conexao.conectar();
            
            String sql = "INSERT INTO filmes (nome, datalancamento, categoria) VALUES (?, ?, ?);";
            
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            consulta.setString(1, f.getNome());
            consulta.setString(2, f.getDatalancamento());
            consulta.setString(3, f.getCategoria());
            
            consulta.execute();
            return true;
        }catch(SQLException sqle) {
            System.out.println("Erro ao cadastrar filme no banco de dados!" + sqle);
            return false;
        }
        
    }
    
   public static List<filmes> listarFilmes(){
       List<filmes> lista = new ArrayList<>();
       try{
       conexaoJDBC conexao = new conexaoJDBC();
       conexao.conectar();
       
       String sql = "SELECT * FROM filmes;";
       PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
       ResultSet resposta = consulta.executeQuery();
       
       while(resposta.next()){
           filmes f = new filmes();
           
           f.setId(resposta.getInt("id"));
           f.setNome(resposta.getString("nome"));
           f.setDatalancamento(resposta.getString("datalancamento"));
           f.setCategoria(resposta.getString("categoria"));
           
           lista.add(f);
       }
       conexao.desconectar();
   }catch(SQLException se){
           System.out.println("Erro ao listar os filmes!" + se);
   }
       return lista;
   }
    
   public static boolean atualizar(filmes f) {
        try {
            conexaoJDBC conexao = new conexaoJDBC();
            conexao.conectar();
            
            String sql = "UPDATE filmes SET nome=?, datalancamento=?, categoria=? WHERE id=?;";
            
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            consulta.setString(1, f.getNome());
            consulta.setString(2, f.getDatalancamento());
            consulta.setString(3, f.getCategoria());
            consulta.setInt(4, f.getId());
            
            consulta.execute();
            conexao.desconectar();
            return true;
        } catch (SQLException sqle) {
            System.out.println("Não foi possível atualizar os dados! Por favor, verifique a conexão e os valores digitados. Detalhes: " + sqle.getMessage());
            return false;
        }
    }
   
   public static boolean excluir(int id) {
        try {
            conexaoJDBC conexao = new conexaoJDBC();
            conexao.conectar();
            
            String sql = "DELETE FROM filmes WHERE id=?;";
            
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            consulta.setInt(1, id);
            
            consulta.execute();
            conexao.desconectar();
            return true;
        } catch (SQLException sqle) {
            System.out.println("Não foi possível excluir o filme! Por favor, verifique se o registro ainda existe no sistema. Detalhes: " + sqle.getMessage());
            return false;
        }
    }
   
   public static List<filmes> buscarPorCategoria(String categoria) {
        List<filmes> lista = new ArrayList<>();
        try {
            conexaoJDBC conexao = new conexaoJDBC();
            conexao.conectar();
            
            String sql = "SELECT * FROM filmes WHERE categoria LIKE ?;";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            consulta.setString(1, "%" + categoria + "%");
            ResultSet resposta = consulta.executeQuery();
            
            while (resposta.next()) {
                filmes f = new filmes();
                f.setId(resposta.getInt("id"));
                f.setNome(resposta.getString("nome"));
                f.setDatalancamento(resposta.getString("datalancamento"));
                f.setCategoria(resposta.getString("categoria"));
                
                lista.add(f);
            }
            conexao.desconectar();
        } catch (SQLException se) {
            System.out.println("Não foi possível realizar a consulta filtrada! Verifique a conexão com o banco. Detalhes: " + se.getMessage());
        }
        return lista;
    }
   
}
