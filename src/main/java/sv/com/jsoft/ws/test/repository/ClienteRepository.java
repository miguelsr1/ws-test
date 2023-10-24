package sv.com.jsoft.ws.test.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sv.com.jsoft.ws.test.model.Cliente;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
}
