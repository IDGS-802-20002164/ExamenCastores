
package examen.rest;

import castores.controller.ControllerMovimiento;
import castores.controller.ControllerProducto;
import com.google.gson.Gson;
import examen_model.Movimiento;
import examen_model.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("movimiento")
public class MovimientoREST {
    
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("tipo") String tipo)
    {
        String out="";
        try 
        {
            ControllerMovimiento objCS = new ControllerMovimiento();
            List<Movimiento> movimiento = new ArrayList<Movimiento>();
            movimiento = objCS.getAll(Integer.parseInt(tipo));
            
            Gson objGS = new Gson();
            out = objGS.toJson(movimiento);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Se produjo un error al cargar el catalogo, vuelva a intentarlo\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
}
