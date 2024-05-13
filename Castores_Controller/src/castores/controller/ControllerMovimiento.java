
package castores.controller;

import castores.db.ConexionMySQL;
import examen_model.Movimiento;
import examen_model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ControllerMovimiento {
     public List<Movimiento> getAll(int tipo) throws Exception
    {
        //  Definir la consulta SQL
        String query = "SELECT * FROM movimientos WHERE tipo = "+tipo+";";
        
        //Generar la lista de sucursales que se va a obtener
        List<Movimiento> movimiento = new ArrayList<Movimiento>();
        
        //Crear un objeto de la conexión a la BD y abrirla
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        
        //Se genera un objeto para poder enviar y ejecutar la sentencia
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //Se ejecuta la sentencia y recibimos el resultado de la consulta
        ResultSet rs = pstmt.executeQuery();
        
        //Recorrer el RS
        while(rs.next())
        {
            //Generar una variable temporal para crear nuevas instancias de Sucursal
            Movimiento p = new Movimiento();
            
            //Llenamos los atributos del objeto con los datos del RS
            p.setId(rs.getInt("id"));
            p.setId_producto(rs.getInt("id_producto"));
            p.setTipo(rs.getInt("tipo"));
            p.setCantidad(rs.getInt("cantidad"));
            p.setid_usuario(rs.getInt("id_usuario"));
            p.setFecha_hora(rs.getString("fecha_hora"));
            
            //Se agrega esa sucursal a la lista de sucursales
            movimiento.add(p);
        }
        
        //Cerramos los objetos
        rs.close();
        pstmt.close();
        connMySQL.close();
        
        //Se devuelve la lista dinamica de sucursales
        return movimiento;
        
    }   
}
