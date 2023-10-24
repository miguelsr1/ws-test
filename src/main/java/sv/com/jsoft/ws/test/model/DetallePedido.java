package sv.com.jsoft.ws.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity(name = "detalle_pedido")
public class DetallePedido extends PanacheEntityBase  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pedido")
    public Integer idDetallePedido;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    public Pedido idPedido;
    @JoinColumn(name = "id_producto")
    @ManyToOne
    public Producto idProducto;
    public Boolean activo;

    public Double cantidad;
}
