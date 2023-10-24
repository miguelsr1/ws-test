package sv.com.jsoft.ws.efact.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sv.com.jsoft.ws.efact.model.Cliente;
import sv.com.jsoft.ws.efact.model.Producto;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
}
