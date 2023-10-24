package sv.com.jsoft.ws.test.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "municipio")
public class Municipio extends PanacheEntityBase {
    @Id
    @Column(name = "id_municipio")
    public Integer idMunicipio;
    public String codigo;
    public String nombre;
    @Column(name = "codigo_departamento")
    public String codigoDepartamento;
}
