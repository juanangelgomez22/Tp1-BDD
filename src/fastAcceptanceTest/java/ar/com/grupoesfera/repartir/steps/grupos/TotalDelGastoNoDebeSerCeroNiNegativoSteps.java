package ar.com.grupoesfera.repartir.steps.grupos;

import ar.com.grupoesfera.repartir.exceptions.DomainException;
import ar.com.grupoesfera.repartir.model.Gasto;
import ar.com.grupoesfera.repartir.model.Grupo;
import ar.com.grupoesfera.repartir.steps.FastCucumberSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class TotalDelGastoNoDebeSerCeroNiNegativoSteps extends FastCucumberSteps {

    private Grupo grupo;
    private Throwable errorLanzado;

    @Dado("un grupo existente con miembros")
    public void unGrupoExistenteConMiembros() {
        grupo = new Grupo();
        grupo.setNombre("Salida");
        grupo.setMiembros(Arrays.asList("Juan", "Maria"));
    }

    @Cuando("el usuario intenta agregar un gasto con monto de {bigdecimal}")
    public void elUsuarioIntentaAgregarUnGastoConMonto(BigDecimal monto) {
        Gasto gastoInvalido = new Gasto();
        gastoInvalido.setMonto(monto);
        errorLanzado = catchThrowable(() -> grupo.agregarGasto(gastoInvalido));
    }

    @Entonces("la operación es rechazada por monto inválido")
    public void laOperacionEsRechazadaPorMontoInvalido() {
        assertThat(errorLanzado)
                .isNotNull()
                .isInstanceOf(DomainException.class);
    }
}