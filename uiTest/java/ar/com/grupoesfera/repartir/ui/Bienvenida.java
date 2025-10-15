package ar.com.grupoesfera.repartir.ui;

import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;

import static org.assertj.core.api.Assertions.*;

public class Bienvenida extends UITest {

    @Test
    public void seMuestraAlInicio() {

        driver.navigate().to(url("/"));

        var bienvenidaDialog = driver.findElement(By.tagName("p-dialog"));

        var contenido = bienvenidaDialog.getText();
        assertThat(contenido).contains("Repartir");

        var iniciarButton = driver.findElement(By.id("iniciarBienvenidaButton"));
        assertThat(iniciarButton.getText()).isEqualTo("Iniciar");
    }

    @Test
    public void sePuedeAgregarGruposLuegoDePresionarIniciar() {

        driver.navigate().to(url("/"));

        var iniciarButton = driver.findElement(By.id("iniciarBienvenidaButton"));
        iniciarButton.click();

        var crearGruposButton = driver.findElement(By.cssSelector("#crearGruposButton button"));
        crearGruposButton.click();
        var nuegoGrupoDialog = driver.findElement(By.cssSelector("app-grupo-nuevo"));
        assertThat(nuegoGrupoDialog.isDisplayed())
                .as("Dialogo de Nuevo Grupo visible")
                .isTrue();
        assertThat(nuegoGrupoDialog.getText())
                .as("Dialogo de Nuebo Grupo con titulo")
                .contains("Nuevo Grupo");
    }

    @Test
    public void noSePuedeAgregarGruposAntesDeIniciar() {

        driver.navigate().to(url("/"));

        var crearGruposButton = driver.findElement(By.cssSelector("#crearGruposButton button"));
        assertThatThrownBy(() -> crearGruposButton.click())
                .as("El boton no es clickeable")
                .isExactlyInstanceOf(ElementClickInterceptedException.class);
    }
}
