package sv.com.jsoft.ws.test.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity(name = "producto")
public class Producto  extends PanacheEntityBase {

    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer idProducto;
    public String codigo;
    public String nombre;
    @Column(name = "precio_unitario")
    public Double precioUnitario;
    public Boolean activo;

    @Column(name = "id_unidad_medida")
    public Integer idUnidadMedida;

}
