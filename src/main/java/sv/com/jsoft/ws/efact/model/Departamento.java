package sv.com.jsoft.ws.efact.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "departamento")
public class Departamento extends PanacheEntityBase {
    @Id
    public String codigo;
    public String nombre;
}
