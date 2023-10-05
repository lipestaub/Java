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
public class UsuarioDAO {
    
    public ArrayList verificarUsuario(Usuario u){

        ArrayList codigo = new ArrayList();
        
        try{
            
            Connection con = ConnectionFactory.getConnection();
            
            PreparedStatement stmt = null;
        
            stmt = con.prepareStatement("SELECT codigoUsuario FROM usuario WHERE nomeUsuario = ? AND senhaUsuario = ?");
            
            stmt.setString(1,u.getNomeUsuario());
            stmt.setString(2,u.getSenhaUsuario());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                codigo.add(rs.getString("codigoUsuario"));
                
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex){
        
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return codigo;
    }
    
}
