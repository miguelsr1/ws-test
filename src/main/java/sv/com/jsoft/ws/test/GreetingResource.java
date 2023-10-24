package sv.com.jsoft.ws.test;

import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.primefaces.model.rest.AutoCompleteSuggestion;
import org.primefaces.model.rest.AutoCompleteSuggestionResponse;
import sv.com.jsoft.ws.test.model.Cliente;
import sv.com.jsoft.ws.test.model.Producto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/hello")
public class GreetingResource {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/all")
    @RolesAllowed({"ADMIN","OPERADOR"})
    public List<Producto> list() {
        return Producto.listAll();
    }


    @GET
    @Path("/autocomplete/producto/")
    @RolesAllowed({"ADMIN","OPERADOR"})
    @Produces({MediaType.APPLICATION_JSON})
    public AutoCompleteSuggestionResponse autocomplete(@QueryParam("query") String query) {
        Log.info("ok");
        String queryLowerCase = query.toLowerCase();
        List<Producto> lstProducto = Producto.listAll();
        return new AutoCompleteSuggestionResponse(lstProducto.stream()
                .filter(t -> t.codigo.toLowerCase().contains(queryLowerCase))
                .map(t -> new AutoCompleteSuggestion(Integer.toString(t.idProducto), t.nombre))
                .collect(Collectors.toList()));
    }

    @GET
    @Path("/autocomplete/cliente/")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"ADMIN","OPERADOR"})
    public AutoCompleteSuggestionResponse autocompleteCliente(@QueryParam("query") String query) {
        Log.info("ok");
        String queryLowerCase = query.toLowerCase();
        List<Cliente> lstCliente = Cliente.listAll();
        return new AutoCompleteSuggestionResponse(lstCliente.stream()
                .filter(t -> (t.nit.toLowerCase().contains(queryLowerCase) ||
                        (t.codigo.toLowerCase().contains(t.codigo)) ||
                        (t.razonSocial.toLowerCase().contains(t.codigo))))
                .map(t -> new AutoCompleteSuggestion(Integer.toString(t.idCliente), t.razonSocial))
                .collect(Collectors.toList()));
    }


    @GET
    @Path("/producto/{codigo}/")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"ADMIN","OPERADOR"})
    public Producto productoByCodigo(@PathParam("codigo") String codigo) {
        Optional<Producto> optional = Producto.findByIdOptional(codigo);
        return optional.orElseThrow(NotFoundException::new);
    }

    @GET
    @Path("/cliente/{codigo}/")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"ADMIN","OPERADOR"})
    public Cliente clienteByCodigo(@PathParam("codigo") String codigo) {
        Optional<Cliente> optional = Cliente.findByIdOptional(codigo);
        return optional.orElseThrow(NotFoundException::new);
    }

}
