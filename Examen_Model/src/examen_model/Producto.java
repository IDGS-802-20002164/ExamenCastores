
package examen_model;

public class Producto {
    private int id;
    private String nombre;
    private int cantidad ;
    private int estatus;
    private int tipo;
    private int idUsuario;
    
    public Producto() {
    }

    public Producto(int id, String nombre, int cantidad, int estatus, int tipo, int idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.estatus = estatus;
        this.tipo = tipo;
        this.idUsuario = idUsuario;
    }

    public Producto(String nombre, int cantidad, int estatus, int tipo, int idUsuario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.estatus = estatus;
        this.tipo = tipo;
        this.idUsuario = idUsuario;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
    
    public int getTipo() {
        return tipo;
    }
    
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", estatus=" + estatus + '}';
    }
}
