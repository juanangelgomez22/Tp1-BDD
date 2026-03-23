package ar.com.grupoesfera.repartir.model;

import java.math.BigDecimal;

public class Gasto {

    private String descripcion;
    private BigDecimal monto;

    public Gasto() {
    }

    public Gasto(String descripcion, int monto) {
        this.descripcion = descripcion;
        this.monto = BigDecimal.valueOf(monto);
    }

    public Gasto(String descripcion, BigDecimal monto) {
        this.descripcion = descripcion;
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto){
        this.monto = monto;
    }
}
