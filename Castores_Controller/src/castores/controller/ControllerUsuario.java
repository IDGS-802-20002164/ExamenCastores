/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castores.controller;

import castores.db.ConexionMySQL;
import examen_model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class ControllerUsuario {
    
      public int insert(Usuario s) throws Exception
    {
        //Definir la sentencia SQL que realiza la inserción de una sucursal en la BD
        String query = "INSERT INTO usuarios (nombre, correo, contrasena, idRol, estatus)"
                       +"VALUES (?, ?, ?, ?, ?)";
        
        //Se declara la variable sobre el que se almacena el id generado
        int idGenerado = -1;
        
        //Se genera un objeto de la conexión y la abrimos
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        System.out.println(conn);
        
        //Generamos un objeto que va a llevar la consulta a la BD y que permita
        //devolver el ID generado, 

               PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                if (pstmt != null) {
                    pstmt.setString(1, s.getNombre());
                    pstmt.setString(2, s.getCorreo());
                    pstmt.setString(3, s.getContrasenia());
                    pstmt.setInt(4, s.getIdRol());
                    pstmt.setInt(5, s.getEstatus());

                    pstmt.executeUpdate();
                } else {
                    throw new Exception("PreparedStatement is null.");
                }
        //Declaramos un objeto que va a guardar el resultado devuelto de la consulta
        ResultSet rs = null;
        


        
        //Solicitamos al PreparedStatement el valor que genero (id)
        rs = pstmt.getGeneratedKeys();
        
        if(rs.next())
        {
            idGenerado = rs.getInt(1);
            s.setIdUsuario(idGenerado);
        }
        
        //Cerramos los objetos de conexión que se abrieron
        rs.close();
        pstmt.close();
        connMySQL.close();
        
        return idGenerado;
        
    }
    
}
