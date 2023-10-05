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
public class MarcaDAO {
    
    public ArrayList listarMarcas(){

        ArrayList marcas = new ArrayList();
        
        try{
            
            Connection con = ConnectionFactory.getConnection();
            
            PreparedStatement stmt = null;
        
            stmt = con.prepareStatement("SELECT nomeMarca FROM marca");
            
            ResultSet rs = stmt.executeQuery();
            
            
            while(rs.next()){
                
                marcas.add(rs.getString("nomeMarca"));
                
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex){
        
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return marcas;
    }
    
}
