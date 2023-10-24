package sv.com.jsoft.ws.efact.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity(name = "tipo_unidad_medida")
public class TipoUnidadMedida extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_unidad_medida")
    public Integer idUnidadMedida;
    public String codigo;
    public String descripcion;
}
