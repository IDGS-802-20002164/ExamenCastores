/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castores.db;

import castores.controller.ControllerLogin;
import castores.controller.ControllerMovimiento;
import castores.controller.ControllerProducto;
import castores.controller.ControllerUsuario;
import examen_model.Movimiento;
import examen_model.Producto;
import examen_model.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author machi
 */
public class Prueba {

    public static void main(String[] args) {
        probarGetAllT();
    }
    
    public static void probarCon() {
        ConexionMySQL objCon = new ConexionMySQL();

        try {
            objCon.open();
            System.out.println(objCon.toString());
            objCon.close();
        } catch (Exception ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    public static void probarInsert() {
    try {
        Usuario s = new Usuario("Jose", "Jose@gmail.com", "1234",1,1);
        ControllerUsuario objCS = new ControllerUsuario();
        if (objCS != null) {
            int idG = objCS.insert(s);
            System.out.println(idG);
        } else {
            System.out.println("ControllerUsuario is null.");
        }
    } catch (Exception ex) {
        System.out.println(ex.toString());
        ex.printStackTrace();
    }
}
    
    public static void probarLogin() {
        try {
            ControllerLogin cl = new ControllerLogin();
            Usuario e = cl.login("jose", "1234");
            System.out.println(e.toString());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void probarGetAllT() {
        try {
            ControllerMovimiento objCS = new ControllerMovimiento();
            List<Movimiento> producto = objCS.getAll(1);

            for (int i = 0; i < producto.size(); i++) {
                System.out.println(producto.get(i).toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void probarDeleteT() {
        try {
            ControllerProducto objCS = new ControllerProducto();
            System.out.println(objCS.delete(20, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
