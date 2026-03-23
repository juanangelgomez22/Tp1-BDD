package ar.com.grupoesfera.repartir.steps;

import ar.com.grupoesfera.repartir.exceptions.DomainException;
import ar.com.grupoesfera.repartir.model.Gasto;
import ar.com.grupoesfera.repartir.model.Grupo;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrupoStepDefinitions extends FastCucumberSteps {
    private Grupo grupo;
    private String mensajeError;

    @Dado("que creo un grupo llamado {string}")
    public void crearGrupo(String nombre) {
        this.grupo = new Grupo(nombre);
    }

    @Entonces("el total del grupo debe ser {int} pesos")
    public void verificarTotal(int totalEsperado) {
        assertEquals(totalEsperado, grupo.getTotalAcumulado().intValue());
    }

    @Dado("que el grupo {string} tiene un total de {int} pesos")
    public void grupoConSaldo(String nombre, int saldoInicial) {
        this.grupo = new Grupo(nombre);
        this.grupo.agregarGasto(new Gasto("Carga inicial", saldoInicial));
    }

    @Cuando("intentó registrar un gasto de {int} pesos por {string}")
    public void intentarRegistrarGasto(int monto, String descripcion) {
        try {
            this.grupo.agregarGasto(new Gasto(descripcion, monto));
        } catch (DomainException e) {
            this.mensajeError = e.getMessage();
        }
    }

    @Entonces("se debe rechazar la operación por {string}")
    public void verificarMensajeError(String errorEsperado) {
        assertEquals(errorEsperado, this.mensajeError);
    }
}