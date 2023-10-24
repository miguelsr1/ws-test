package sv.com.jsoft.ws.efact.model.dto;

import lombok.Builder;
import lombok.Data;

public class ResponseDto {
    private Integer val;
    private String mensaje;

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
