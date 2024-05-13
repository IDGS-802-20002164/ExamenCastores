/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.rest;

import castores.controller.ControllerProducto;
import com.google.gson.Gson;
import examen_model.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("producto")
public class ProductoREST {
    
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("estatus") String estatus)
    {
        String out="";
        try 
        {
            ControllerProducto objCS = new ControllerProducto();
            List<Producto> producto = new ArrayList<Producto>();
            producto = objCS.getAll(Integer.parseInt(estatus));
            
            Gson objGS = new Gson();
            out = objGS.toJson(producto);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Se produjo un error al cargar el catalogo, vuelva a intentarlo\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
     @Path("insert")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@QueryParam("p") String p)
    {
        String out="";
        try
        {
            //Creamos un objeto de la libreria Gson para convertir un json en objeto de java
            Gson objGS = new Gson();
            Producto pro = objGS.fromJson(p, Producto.class);
            //Se crea un objeto del controlador
            ControllerProducto objCS = new ControllerProducto();
            int idG = objCS.insert(pro);
            out = "{\"idGenerado\":"+idG+"}";
        }catch(Exception ex)
        {
             ex.printStackTrace();
            out = "{\"error\":\"Hubo un fallo en la inserción, "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("delete")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") String id, @QueryParam("estatus") String estatus)
    {
        String out = "";
        try
        {
            ControllerProducto objCS = new ControllerProducto();
            objCS.delete(Integer.parseInt(id),Integer.parseInt(estatus));
            out = "{\"result\":\"La eliminación del producto resultó exitosa\"}";
        } catch(Exception ex)
        {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un fallo en la inserción, "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("activate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response activate(@QueryParam("id") String id, @QueryParam("estatus") String estatus)
    {
        String out = "";
        try
        {
            ControllerProducto objCS = new ControllerProducto();
            objCS.activate(Integer.parseInt(id),Integer.parseInt(estatus));
            out = "{\"result\":\"La eliminación del producto resultó exitosa\"}";
        } catch(Exception ex)
        {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un fallo en la inserción, "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("update")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@QueryParam("p") String p)
    {
        String out = "";
        try
        {   
            Gson objGS = new Gson();
            Producto pro = objGS.fromJson(p, Producto.class);
            ControllerProducto objCS = new ControllerProducto();
            objCS.update(pro);
            out = "{\"resultado\":\"La actualización del producto resultó exitosa\"}";
        } catch(Exception ex)
        {
            ex.printStackTrace();
            out = "{\"error\":\"Hubo un fallo en la inserción, "
                    + "Vuelve a intentarlo, o llama al administrador del sistema\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
}
