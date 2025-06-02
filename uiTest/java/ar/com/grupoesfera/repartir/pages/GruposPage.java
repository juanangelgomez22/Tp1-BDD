package ar.com.grupoesfera.repartir.pages;

import ar.com.grupoesfera.repartir.ui.UITest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class GruposPage extends UITest.PageObject {

    public GruposPage(UITest test) {

        super(test);
    }

    public GruposPage ir() {

        this.driver().navigate().to(url("/"));
        var iniciarButton = this.driver().findElement(By.id("iniciarBienvenidaButton"));
        iniciarButton.click();

        return this;
    }

    public GruposPage clickEnCrear() {

        this.driver().findElement(By.id("crearGruposButton")).click();

        return this;
    }

    public GruposPage tipearNombre(String nombre) {

        this.driver().findElement(By.id("nombreGrupoNuevoInput")).sendKeys(nombre);
        return this;
    }

    public GruposPage tipearMiembro(String miembro) {

        this.driver().findElement(By.id("miembrosGrupoNuevoInput")).sendKeys(miembro + "\n");
        return this;
    }

    public GruposPage clickEnGuardar() {

        this.driver().findElement(By.id("guardarGrupoNuevoButton")).click();
        return this;
    }

    public String leerNotificacionExito() {
        var wait = new WebDriverWait(this.driver(), 2);
        var mensajesToast = wait.until(visibilityOfElementLocated(By.id("mensajesToast")));
        wait.until(textToBePresentInElement(mensajesToast, "Éxito"));
        return mensajesToast.getText();
    }

    public String leerNotificacionError() {
        var wait = new WebDriverWait(this.driver(), 2);
        var mensajesToast = wait.until(visibilityOfElementLocated(By.id("mensajesToast")));
        wait.until(textToBePresentInElement(mensajesToast, "Error"));
        return mensajesToast.getText();
    }

}
