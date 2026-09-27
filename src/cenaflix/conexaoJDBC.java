package cenaflix;

import java.sql.*;

public class conexaoJDBC {
    private Connection conexao;
    
    public Connection getConexao(){
        return conexao;
    }
    
    public void conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/cenaflix", "root", "emersonBardos/01");
            System.out.println("Conexão bem sucedida!");
        }catch(ClassNotFoundException cnfe){
            System.out.println("Falha ao tentar conectar, classe não encontrada!");
        }catch(SQLException sqle){
            System.out.println("Falha ao tentar conectar ao banco!");
        }
    }
    public void desconectar(){
        try{
            if(conexao != null && !conexao.isClosed()){
                conexao.close();
                System.out.println("Desconectado do banco com sucesso!");
            }
        }catch(SQLException se){
            System.out.println("Erro ao desconectar" + se);
        }
        
        
    }
}
