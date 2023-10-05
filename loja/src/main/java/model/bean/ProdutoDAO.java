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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class ProdutoDAO {
    
    public void cadastro(Produto p){

        try{
            
            Connection con = ConnectionFactory.getConnection();
        
            PreparedStatement stmt = null;
        
            stmt = con.prepareStatement("INSERT INTO produto (descricaoProduto, marcaProduto, corProduto, precoProduto) VALUES(?,(SELECT codigoMarca FROM marca WHERE nomeMarca = ?),(SELECT codigoCor FROM cor WHERE descricaoCor = ?),?)");
        
            stmt.setString(1,p.getDescricaoProduto());
            stmt.setString(2,p.getMarcaProduto());
            stmt.setString(3,p.getCorProduto());
            stmt.setDouble(4,p.getPrecoProduto());
            
            stmt.execute();
            
        }catch(SQLException ex){
        
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            
            //ConnectionFactory.closeConnection(con, stmt);
            
        }

    }
    
    public ArrayList listarProdutos(){

        ArrayList produtos = new ArrayList();
        
        try{
            
            Connection con = ConnectionFactory.getConnection();
            
            PreparedStatement stmt = null;
        
            stmt = con.prepareStatement("SELECT descricaoProduto FROM produto");
            
            ResultSet rs = stmt.executeQuery();
            
            
            while(rs.next()){
                
                produtos.add(rs.getString("descricaoProduto"));
                
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex){
        
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return produtos;
    }
    
    public ArrayList buscaCodigos(){

        ArrayList codigos = new ArrayList();
        
        try{
            
            Connection con = ConnectionFactory.getConnection();
            
            PreparedStatement stmt = null;
        
            stmt = con.prepareStatement("SELECT codigoProduto FROM produto");
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                codigos.add(rs.getInt("codigoProduto"));
                
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex){
        
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally {
            
           // ConnectionFactory.closeConnection(con, stmt);
            
        }
        
        return codigos;
    }
    
}
