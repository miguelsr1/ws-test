package sv.com.jsoft.ws.test.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "pedido")
public class Pedido extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    public Integer idPedido;
    @Column(name = "id_cliente")
    public Integer idCliente;
    @Column(name = "id_estado_pedido")
    public Integer idEstadoPedido;
    @Column(name = "fecha_registro")
    public LocalDateTime fechaRegistro;
    public String user;

    @OneToMany(mappedBy = "idPedido", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    public List<DetallePedido> detallePedidoList;
}
