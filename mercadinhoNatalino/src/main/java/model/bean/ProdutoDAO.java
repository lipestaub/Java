/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class ProdutoDAO {
    
    public void create(Produto p){

        try{
            
            Connection con = ConnectionFactory.getConnection();
        
            PreparedStatement stmt = null;
        
            stmt = con.prepareStatement("INSERT INTO produto (descricaoProduto, quantidadeProduto, precoUnitarioProduto) VALUES(?,?,?)");
        
            stmt.setString(1,p.getDescricaoProduto());
            stmt.setInt(2,p.getQuantidadeProduto());
            stmt.setDouble(3,p.getPrecoUnitarioProduto());
            
            stmt.execute();
            
        }catch(SQLException ex){
        
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            
            //ConnectionFactory.closeConnection(con, stmt);
            
        }

    }
    
}
