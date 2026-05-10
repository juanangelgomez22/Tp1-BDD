package ar.com.grupoesfera.repartir.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nombre;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "miembro")
    private List<String> miembros;

    private BigDecimal total;

    public Grupo() {
    }

    public Grupo(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Grupo(String nombre) {
        this.nombre = nombre;
        this.total = BigDecimal.ZERO;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<String> miembros) {
        this.miembros = miembros;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean estaFormado() {

        return (miembros != null) && (miembros.size() > 1);
    }

    public void agregarGasto(Gasto gasto) {
        if (gasto == null || gasto.getMonto() == null) {
            throw new IllegalArgumentException("El gasto no puede ser nulo");
        }
        if (gasto.getMonto().compareTo(BigDecimal.ZERO) < 0) {
            throw new ar.com.grupoesfera.repartir.exceptions.DomainException("El monto del gasto no debe ser negativo");
        }

        if (this.total == null) {
            this.total = BigDecimal.ZERO;
        }

        this.total = this.total.add(gasto.getMonto());
    }

    @com.fasterxml.jackson.annotation.JsonIgnore
    public BigDecimal getTotalAcumulado() {
        return this.total == null ? BigDecimal.ZERO : this.total;
    }
}
