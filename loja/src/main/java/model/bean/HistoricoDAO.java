/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class HistoricoDAO {
    
    public void entrada(Historico h){

        try{
            
            Connection con = ConnectionFactory.getConnection();
        
            PreparedStatement stmt = null;
        
            stmt = con.prepareStatement("INSERT INTO historico (codigoProduto, operacao, quantidade, dataAlteracao) VALUES((SELECT codigoProduto FROM produto WHERE descricaoProduto = ?),'1',?,CURRENT_DATE)");
        
            stmt.setString(1,h.getDescricaoProduto());
            stmt.setInt(2,h.getQuantidade());
            
            stmt.execute();
            
        }catch(SQLException ex){
        
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            
            //ConnectionFactory.closeConnection(con, stmt);
            
        }

    }
    
    public void saida(Historico h){

        try{
            
            Connection con = ConnectionFactory.getConnection();
        
            PreparedStatement stmt = null;
        
            stmt = con.prepareStatement("INSERT INTO historico (codigoProduto, operacao, quantidade, dataAlteracao) VALUES((SELECT codigoProduto FROM produto WHERE descricaoProduto = ?),'2',?,CURRENT_DATE)");
        
            stmt.setString(1,h.getDescricaoProduto());
            stmt.setInt(2,h.getQuantidade());
            
            stmt.execute();
            
        }catch(SQLException ex){
        
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            
            //ConnectionFactory.closeConnection(con, stmt);
            
        }

    }
    
    public ArrayList tabelaTodos(Historico h){

        ArrayList rows = new ArrayList();
        
        try{
            
            Connection con = ConnectionFactory.getConnection();
            
            PreparedStatement stmt = null;
        
            stmt = con.prepareStatement("SELECT (SELECT descricaoProduto FROM produto WHERE codigoProduto = A.codigoProduto) AS descricaoProduto, SUM(quantidade) - COALESCE((SELECT SUM(quantidade) FROM historico WHERE codigoProduto = A.codigoProduto AND operacao = 2),0) AS quantidade FROM historico A WHERE  codigoProduto = ? AND A.operacao = 1;");
            
            stmt.setInt(1,h.getCodigoProduto());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                rows.add(rs.getString("descricaoProduto"));
                rows.add(rs.getInt("quantidade"));
                
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex){
        
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return rows;
    }
    
    public ArrayList tabelaId(Historico h){

        ArrayList rows = new ArrayList();
        
        try{
            
            Connection con = ConnectionFactory.getConnection();
            
            PreparedStatement stmt = null;
        
            stmt = con.prepareStatement("SELECT (SELECT descricaoProduto FROM produto WHERE codigoProduto = A.codigoProduto) AS descricaoProduto, SUM(quantidade) - COALESCE((SELECT SUM(quantidade) FROM historico WHERE codigoProduto = A.codigoProduto AND operacao = 2),0) AS quantidade FROM historico A WHERE  A.codigoProduto = (SELECT codigoProduto FROM produto WHERE  descricaoProduto = ?) AND A.operacao = 1;");
            
            stmt.setString(1,h.getDescricaoProduto());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                rows.add(rs.getString("descricaoProduto"));
                rows.add(rs.getInt("quantidade"));
                
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex){
        
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return rows;
    }
    
    public ArrayList controleEstoque(Historico h){

        ArrayList qtd = new ArrayList();
        
        try{
            
            Connection con = ConnectionFactory.getConnection();
            
            PreparedStatement stmt = null;
        
            stmt = con.prepareStatement("SELECT SUM(quantidade) - COALESCE((SELECT SUM(quantidade) FROM historico WHERE codigoProduto = A.codigoProduto AND operacao = 2),0) AS quantidade FROM historico A WHERE  A.codigoProduto = (SELECT codigoProduto FROM produto WHERE  descricaoProduto = ?) AND A.operacao = 1;");
            
            stmt.setString(1,h.getDescricaoProduto());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){

                qtd.add(rs.getInt("quantidade"));
                
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex){
        
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return qtd;
    }
    
}
