
package castores.controller;

import castores.db.ConexionMySQL;
import examen_model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ControllerLogin {
    public Usuario login(String nombreUsuario, String contrasenia) throws Exception {
        //Definir la consulta que se va a ejecutar
        String query = "SELECT * FROM usuarios WHERE nombre=? AND contrasena=?";

        //Generar el objeto de la conexion
        ConexionMySQL connMySQL = new ConexionMySQL();
        //Abrimos la conexion
        Connection conn = connMySQL.open();
        //Objeto para ejecutar la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        //Llenar parametros de la consulta
        pstmt.setString(1, nombreUsuario);
        pstmt.setString(2, contrasenia);
        //Obbjeto para recibir los datos
        ResultSet rs = pstmt.executeQuery();

        //Objeto de tipo empleado
        Usuario e = null;

        if (rs.next()) {
            e = fill(rs);
        }
        //Cerramos los objetos de uso para la BD
        rs.close();
        pstmt.close();
        conn.close();

        return e;
    }

    private Usuario fill(ResultSet rs) throws Exception {
        //Una variable temporal para crear nuevos objetos de tipo Empleado:
        Usuario e = new Usuario();

        //Establecemos sus datos personales:
        e.setNombre(rs.getString("nombre"));
        e.setIdUsuario(rs.getInt("idUsuario"));
        e.setCorreo(rs.getString("correo"));
        e.setContrasenia(rs.getString("contrasena"));
        e.setIdRol(rs.getInt("idRol"));
        e.setEstatus(rs.getInt("estatus"));

        return e;
    }
}
