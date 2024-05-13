
package castores.controller;

import castores.db.ConexionMySQL;
import examen_model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerProducto {
   public int insert(Producto p) throws Exception
    {
        //Definir la sentencia SQL que realiza la inserción de una sucursal en la BD
        String query = "INSERT INTO productos (nombre, cantidad,estatus)"
                       +"VALUES (?, ?, ?)";
        
        //Se declara la variable sobre el que se almacena el id generado
        int idProGenerado = -1;
        
        //Se genera un objeto de la conexión y la abrimos
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        
        //Generamos un objeto que va a llevar la consulta a la BD y que permita
        //devolver el ID generado, 
        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        //Declaramos un objeto que va a guardar el resultado devuelto de la consulta
        ResultSet rs = null;
        
        //Terminar de armar la sentencia / Cargar el objeto pstmt
        pstmt.setString(1, p.getNombre());
        pstmt.setInt(2, p.getCantidad());
        pstmt.setInt(3, p.getEstatus());
        
        //Ejecutamos la consulta
        pstmt.executeUpdate();
        
        //Solicitamos al PreparedStatement el valor que genero (id)
        rs = pstmt.getGeneratedKeys();
        
        if(rs.next())
        {
            idProGenerado = rs.getInt(1);
            p.setId(idProGenerado);
        }
        
        //Cerramos los objetos de conexión que se abrieron
        rs.close();
        pstmt.close();
        connMySQL.close();
        
        return idProGenerado;
        
    }  
      
    public List<Producto> getAll(int estatus) throws Exception
    {
        //  Definir la consulta SQL
        String query = "SELECT * FROM productos WHERE estatus = "+estatus+";";
        
        //Generar la lista de sucursales que se va a obtener
        List<Producto> producto = new ArrayList<Producto>();
        
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
            Producto p= new Producto();
            
            //Llenamos los atributos del objeto con los datos del RS
            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            p.setCantidad(rs.getInt("cantidad"));
            p.setEstatus(rs.getInt("estatus"));
            
            //Se agrega esa sucursal a la lista de sucursales
            producto.add(p);
        }
        
        //Cerramos los objetos
        rs.close();
        pstmt.close();
        connMySQL.close();
        
        //Se devuelve la lista dinamica de sucursales
        return producto;
        
    }   
    
    
       public boolean delete(int id, int estatus) throws Exception
    {
        //Generar la consulta
        String query = "UPDATE productos SET estatus = "+estatus+" WHERE id = "+id+";";
        
        //Generar la variable boleana
        boolean r = false;
        
        //Generamos los objetos de conexión y abrirlos
        ConexionMySQL objConMySQL = new ConexionMySQL();
        Connection conn = objConMySQL.open();
        
        //Se genera el objeto que lleva la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //Se genera un objeto para recibir el resultado de la consulta
        int res = pstmt.executeUpdate();
        
        if(res == 1){
            r = true;
        }
        
        //Cerramos los objetos de la conexión con la BD
        pstmt.close();
        conn.close();
        
        return r;
    }
       
         public boolean activate(int id, int estatus) throws Exception
    {
        //Generar la consulta
        String query = "UPDATE productos SET estatus = "+estatus+" WHERE id = "+id+";";
        
        //Generar la variable boleana
        boolean r = false;
        
        //Generamos los objetos de conexión y abrirlos
        ConexionMySQL objConMySQL = new ConexionMySQL();
        Connection conn = objConMySQL.open();
        
        //Se genera el objeto que lleva la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //Se genera un objeto para recibir el resultado de la consulta
        int res = pstmt.executeUpdate();
        
        if(res == 1){
            r = true;
        }
        
        //Cerramos los objetos de la conexión con la BD
        pstmt.close();
        conn.close();
        
        return r;
    }
         
         public boolean update(Producto p) throws Exception
    {
        //Generar consulta
       String query = "{CALL ActualizarProductoConMovimiento(?, ?, ?, ?, ?, ?)}";
       
            ConexionMySQL objConMySQL = new ConexionMySQL();
        Connection conn = objConMySQL.open();
        
    PreparedStatement pstmt = conn.prepareStatement(query);
    pstmt.setInt(1, p.getId());
    pstmt.setString(2, p.getNombre());
    pstmt.setInt(3, p.getTipo());
    pstmt.setInt(4, p.getCantidad());
    pstmt.setInt(5, p.getEstatus());
    pstmt.setInt(6, p.getIdUsuario());
    
    pstmt.execute();


        
        //Generar un boleano
        boolean r = false;
        
        //Generamos los objetos de conexión y abrirlos
   
        
        //Se genera el objeto que lleva la consulta

        
        //Se genera un objeto para recibir el resultado de la consulta
        int rs = pstmt.executeUpdate();
        if(rs == 1){
            r = true;
        }

        //Cerramos los objetos de la conexión con la BD
        pstmt.close();
        conn.close();
        
        return r;
        
    }
}
