package sv.com.jsoft.ws.efact.rest;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.exception.ConstraintViolationException;
import sv.com.jsoft.ws.efact.model.Cliente;
import sv.com.jsoft.ws.efact.model.dto.ResponseDto;
import sv.com.jsoft.ws.efact.repository.ClienteRepository;

import java.util.List;

@Path("cliente")
public class ClienteResource {

    @Inject
    ClienteRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> findAll(){
        return Cliente.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(Cliente cliente){
        ResponseDto responseDto = new ResponseDto();
        try{
            Log.info("/POST: "+cliente);
            cliente.persist();
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
    public Response update(@PathParam("id") Integer id, Cliente cliente){
        ResponseDto responseDto = new ResponseDto();
        try{
            Log.info("/PUT/{"+id+"}/: "+cliente);
            Cliente clienteTmp = Cliente.findById(id);
            clienteTmp.codigo = cliente.codigo;
            clienteTmp.activo = cliente.activo;
            clienteTmp.direccion = cliente.direccion;
            clienteTmp.nit = cliente.nit;
            clienteTmp.nrc = cliente.nrc;
            clienteTmp.email = cliente.email;
            clienteTmp.documentoContacto = cliente.documentoContacto;
            clienteTmp.nombreComercial = cliente.nombreComercial;
            clienteTmp.idMunicipio = cliente.idMunicipio;
            clienteTmp.nombreContacto = cliente.nombreContacto;
            clienteTmp.telefono = cliente.telefono;
            clienteTmp.razonSocial = cliente.razonSocial;
            clienteTmp.tipoDocumento = cliente.tipoDocumento;

            clienteTmp.persist();

            responseDto.setVal(0);
            responseDto.setMensaje("Registro actualizado exitosamente.");
        }catch(ConstraintViolationException ex){
            responseDto.setVal(1);
            responseDto.setMensaje("Ocurrio un error interno. "+ ex.getMessage());
        }
        return Response.ok(responseDto).build();
    }
}
