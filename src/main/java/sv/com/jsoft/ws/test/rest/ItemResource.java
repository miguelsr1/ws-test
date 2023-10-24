package sv.com.jsoft.ws.test.rest;

import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.exception.ConstraintViolationException;
import sv.com.jsoft.ws.test.model.Producto;
import sv.com.jsoft.ws.test.model.TipoUnidadMedida;
import sv.com.jsoft.ws.test.model.dto.ResponseDto;

import java.util.List;

@Path("item")
public class ItemResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "OPERADOR"})
    public List<TipoUnidadMedida> findAll(){
        return TipoUnidadMedida.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("ADMIN")
    public Response save(Producto producto){
        ResponseDto responseDto = new ResponseDto();
        try{
            Log.info("/POST: "+producto);
            producto.persist();
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed("ADMIN")
    public Response update(@PathParam("id") Integer id, Producto producto){
        ResponseDto responseDto = new ResponseDto();
        try{
            Log.info("/PUT/{"+id+"}/: "+producto.toString());
            Producto productoTmp = Producto.findById(id);
            productoTmp.codigo = producto.codigo;
            productoTmp.activo = producto.activo;
            productoTmp.idProducto = producto.idProducto;
            productoTmp.nombre = producto.nombre;
            productoTmp.idUnidadMedida = producto.idUnidadMedida;
            productoTmp.precioUnitario = producto.precioUnitario;

            productoTmp.persist();

            responseDto.setVal(0);
            responseDto.setMensaje("Registro actualizado exitosamente.");
        }catch(ConstraintViolationException ex){
            responseDto.setVal(1);
            responseDto.setMensaje("Ocurrio un error interno. "+ ex.getMessage());
        }
        return Response.ok(responseDto).build();
    }
}
