package sv.com.jsoft.ws.test.rest;

import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.exception.ConstraintViolationException;
import sv.com.jsoft.ws.test.model.TipoUnidadMedida;
import sv.com.jsoft.ws.test.model.dto.ResponseDto;

import java.util.List;

@Path("tipoUnidad")
public class TipoUnidadResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "OPERADOR"})
    public List<TipoUnidadMedida> findAll() {
        return TipoUnidadMedida.listAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("ADMIN")
    public Response save(TipoUnidadMedida tipoUnidad){
        ResponseDto responseDto = new ResponseDto();
        try{
            Log.info("/POST: "+tipoUnidad);
            tipoUnidad.persist();
            responseDto.setVal(0);
            responseDto.setMensaje("Registro almacenado exitosamente.");
        }catch(ConstraintViolationException ex){
            responseDto.setVal(1);
            responseDto.setMensaje("Ocurrio un error interno. "+ex.getMessage());
        }
        return Response.ok(responseDto).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("ADMIN")
    public Response update(@PathParam("id") Integer id, TipoUnidadMedida tipoUnidad){
        ResponseDto responseDto = new ResponseDto();
        try{
            Log.info("/PUT: "+tipoUnidad);
            TipoUnidadMedida unidadTemp = TipoUnidadMedida.findById(id);
            unidadTemp.descripcion = tipoUnidad.descripcion;

            unidadTemp.persist();

            responseDto.setVal(0);
            responseDto.setMensaje("Registro almacenado exitosamente.");
        }catch(ConstraintViolationException ex){
            responseDto.setVal(1);
            responseDto.setMensaje("Ocurrio un error interno. "+ex.getMessage());
        }
        return Response.ok(responseDto).build();
    }
}
