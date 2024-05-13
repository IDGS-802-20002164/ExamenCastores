
package examen_model;
import java.security.Timestamp;
import java.util.Date;

public class Movimiento {
    
    private int id;
    private int id_producto;
    private int tipo;
    private int cantidad;
    private int id_usuario;
    private String fecha_hora;
    
    public Movimiento() {
    }

    public Movimiento(int id, int id_producto, int tipo, int cantidad, int id_usuario, String fecha_hora) {
        this.id = id;
        this.id_producto = id_producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.id_usuario = id_usuario;
        this.fecha_hora = fecha_hora;
    }

    public Movimiento(int id_producto, int tipo, int cantidad, int id_usuario, String fecha_hora) {
        this.id_producto = id_producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.id_usuario = id_usuario;
        this.fecha_hora = fecha_hora;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setid_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }


    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", id_producto=" + id_producto + ", tipo=" + tipo + ", cantidad=" + cantidad +", id_usuario=" + id_usuario +", fecha_hora="+fecha_hora+ '}';
    }
}
