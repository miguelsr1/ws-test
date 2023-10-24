package sv.com.jsoft.ws.test.rest;

import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.exception.ConstraintViolationException;
import sv.com.jsoft.ws.test.model.DetallePedido;
import sv.com.jsoft.ws.test.model.Pedido;
import sv.com.jsoft.ws.test.model.dto.ResponseDto;

import java.util.List;

@Path("pedido")
public class PedidoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "OPERADOR"})
    public List<Pedido> findAll(){
        return Pedido.listAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed({"ADMIN", "OPERADOR"})
    public Response save(Pedido pedido){
        ResponseDto responseDto = new ResponseDto();
        try{
            Log.info("/POST: "+pedido);
            List<DetallePedido> lstTmp = pedido.detallePedidoList;

            pedido.detallePedidoList = null;
            pedido.persist();

            for (DetallePedido detallePedido : lstTmp) {
                detallePedido.idPedido = pedido;
            }

            pedido.detallePedidoList = lstTmp;

            pedido.persist();

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
    @RolesAllowed({"ADMIN", "OPERADOR"})
    public Response update(@PathParam("id") Integer id,  Pedido pedido){
        ResponseDto responseDto = new ResponseDto();
        try{
            Log.info("/PUT: "+pedido);
            Pedido pedidoTemp = Pedido.findById(id);
            pedidoTemp.idEstadoPedido = pedido.idEstadoPedido;

            pedidoTemp.persist();

            responseDto.setVal(0);
            responseDto.setMensaje("Registro almacenado exitosamente.");
        }catch(ConstraintViolationException ex){
            responseDto.setVal(1);
            responseDto.setMensaje("Ocurrio un error interno. "+ex.getMessage());
        }
        return Response.ok(responseDto).build();
    }
}
