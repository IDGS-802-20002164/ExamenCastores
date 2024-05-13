
package castores.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL {

    Connection conexion;
    
    public Connection open() throws Exception
    {

            String driver = "com.mysql.jdbc.Driver";
        
        //Establecemos la ruta de conexión
        String url = "jdbc:mysql://127.0.0.1:3306/examen";
        
        //Establecemos los valores de los permisos de acceso
        String user = "root";
        String password = ""; //Se coloca la contraseña el que tu hayas especificado en tu SGBD 
        
        //Dar de alta el uso del driver
        Class.forName(driver);
        
        //Abrir la conexión
        conexion = DriverManager.getConnection(url, user, password);
        
        //Retornamos la conexión que se creo y abrirlo
       
        return conexion;
    }
    
    /**
     * Este método es para cerrar la conexión
     */
    public void close()
    {
        try{
        if(conexion!=null){
            conexion.close();
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
