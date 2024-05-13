/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.rest;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import examen_model.Usuario;
import castores.controller.ControllerUsuario;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;

@Path("usuario")
public class UsuarioREST {
   
    @Path("insert")
@POST // Cambiado a POST
@Produces(MediaType.APPLICATION_JSON)
public Response insert(String jsonData) // Cambiado a recibir un JSON en el cuerpo
{
    String out = "";
    try
    {
        Gson objGS = new Gson();
        Usuario suc = objGS.fromJson(jsonData, Usuario.class);
        ControllerUsuario objCS = new ControllerUsuario();
        int idG = objCS.insert(suc);
        out = "{\"idGenerado\":" + idG + "}";
    }
    catch(Exception ex)
    {
        out = "{\"error\":\"Hubo un fallo en la inserción, Vuelve a intentarlo, o llama al administrador del sistema\"}"+ex;
    }
    return Response.status(Response.Status.OK).entity(out).build();
}

}
