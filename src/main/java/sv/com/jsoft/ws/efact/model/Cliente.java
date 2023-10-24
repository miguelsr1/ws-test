package sv.com.jsoft.ws.efact.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

@Entity(name = "cliente")
public class Cliente extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    public Integer idCliente;
    public String codigo;
    public String nit;
    public String nrc;
    public String email;
    @Column(name = "id_municipio")
    public Integer idMunicipio;
    public String direccion;
    public String telefono;
    @Column(name = "nombre_contacto")
    public String nombreContacto;
    @Column(name = "razon_social")
    public String razonSocial;
    @Column(name = "nombre_comercial")
    public String nombreComercial;
    @Column(name = "documento_contacto")
    public String documentoContacto;
    @Column(name = "tipo_documento")
    public Integer tipoDocumento;
    public Boolean activo;

}
