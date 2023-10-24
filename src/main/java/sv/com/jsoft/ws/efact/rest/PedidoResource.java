package sv.com.jsoft.ws.efact.rest;

import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.exception.ConstraintViolationException;
import sv.com.jsoft.ws.efact.model.DetallePedido;
import sv.com.jsoft.ws.efact.model.Pedido;
import sv.com.jsoft.ws.efact.model.TipoUnidadMedida;
import sv.com.jsoft.ws.efact.model.dto.ResponseDto;

import java.util.List;

@Path("pedido")
public class PedidoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pedido> findAll(){
        return Pedido.listAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
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
