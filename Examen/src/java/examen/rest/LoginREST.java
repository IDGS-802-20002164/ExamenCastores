
package examen.rest;

import castores.controller.ControllerLogin;
import com.google.gson.Gson;
import examen_model.Usuario;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("log")
public class LoginREST {
    @Path("in")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response in(@FormParam("nU") @DefaultValue("0") String nU,@FormParam("c") @DefaultValue("0") String c){
        String out="";
        try {
            ControllerLogin cL=new ControllerLogin();
            Usuario e = cL.login(nU, c);
            Gson objGS = new Gson();
            out = objGS.toJson(e);
        } catch (Exception ex) {
           ex.printStackTrace();
           out = "{\"error\":\"Hubo un fallo en el acceso,verificar la información\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
