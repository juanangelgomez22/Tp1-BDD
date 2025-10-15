import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Importaciones asumidas de las clases de dominio del proyecto Repartir
import com.repartir.dominio.Grupo;
import com.repartir.dominio.Item;
import com.repartir.dominio.Persona;

/**
 * Prueba de Aceptación Rápida para la Regla:
 * Gestión de la asignación de pagos a items compartidos
 * * Escenario 1: El costo del ítem es cubierto exactamente por los pagos
 */
public class GestionDePagosItemFastAcceptanceTest {

    //estas son las del escenario 1
    @Test
    void elCostoDelItemEsCubiertoExactamentePorLosPagos() {
        // GIVEN: Preparación del escenario
        
        // 1. Crear el grupo y las personas
        Grupo grupo = new Grupo("Cena de Amigos");
        
        // Suponemos que el método 'agregarPersona' devuelve la instancia de Persona creada/añadida.
        Persona ana = grupo.agregarPersona("Ana"); 
        Persona beto = grupo.agregarPersona("Beto");
        
        // 2. Crear el ítem con un costo de $1000
        // Suponemos que 'crearItem' requiere el nombre y el costo total.
        Item pizzaGrande = grupo.crearItem("Pizza Grande", 1000.0); 

        // 3. Asignar personas al ítem
        // Suponemos un método para asignar responsables al pago del ítem.
        pizzaGrande.asignarPersona(ana); 
        pizzaGrande.asignarPersona(beto); 

        // WHEN: Ejecución de las acciones
        
        // Registrar pagos. (Ana paga $500, Beto paga $500)
        // Suponemos un método 'registrarPago' que toma la persona y el monto.
        pizzaGrande.registrarPago(ana, 500.0); 
        pizzaGrande.registrarPago(beto, 500.0); 

        // THEN: Verificación del resultado
        
        // 1. Obtener el saldo pendiente del ítem
        // Suponemos un método 'getSaldoPendiente' en la clase Item.
        Double saldoPendiente = pizzaGrande.getSaldoPendiente(); 

        // 2. Verificar que el saldo sea $0
        // Utilizamos un delta (0.001) para comparar valores Double con precisión.
        assertEquals(0.0, saldoPendiente, 0.001, 
            "El saldo pendiente para el ítem 'Pizza Grande' debe ser $0 después de los pagos que cubren el total.");
    }

    //Estas son las pruebas del escenario 2
    @Test
    void elCostoDelItemNoEsCubiertoEnSuTotalidadDebeArrojarError() {
        // GIVEN: Preparación del escenario
        
        // 1. Crear el grupo y las personas
        Grupo grupo = new Grupo("Cena de Amigos");
        Persona ana = grupo.agregarPersona("Ana"); 
        Persona beto = grupo.agregarPersona("Beto");
        
        // 2. Crear el ítem con un costo de $1000
        Item pizzaGrande = grupo.crearItem("Pizza Grande", 1000.0); 

        // 3. Asignar personas al ítem
        pizzaGrande.asignarPersona(ana); 
        pizzaGrande.asignarPersona(beto); 

        // 4. Registrar pagos incompletos (400 + 500 = 900. Saldo deudor: 100)
        pizzaGrande.registrarPago(ana, 400.0); 
        pizzaGrande.registrarPago(beto, 500.0);
}