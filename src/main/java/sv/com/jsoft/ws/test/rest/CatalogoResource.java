package sv.com.jsoft.ws.test.rest;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import sv.com.jsoft.ws.test.model.Departamento;
import sv.com.jsoft.ws.test.model.Municipio;

import java.util.List;

@Path("/catalogos")
public class CatalogoResource {

    @GET
    @Path("/departamento")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public List<Departamento> getDepartamentos(){
        return Departamento.listAll();
    }

    @GET
    @Path("/municipios/{cod}")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public List<Municipio> getMunicipioByDepa(@PathParam("cod") String codigoDepa){
        return Municipio.list("codigoDepartamento=?1", codigoDepa);
    }

    @GET
    @Path("/municipio/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Municipio getMunicipioById(@PathParam("id") Integer id){
        return Municipio.findById(id);
    }
}
