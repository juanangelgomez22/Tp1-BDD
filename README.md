TP1: BDD - Proyecto Repartir 2024
Este repositorio contiene la implementación de una nueva regla de negocio para el sistema de gestión de gastos grupales "Repartir", desarrollada siguiendo la metodología Behavior Driven Development (BDD).

Regla Implementada
"El total de un grupo no debe ser negativo"

Se ha restringido la posibilidad de registrar gastos que resulten en un saldo acumulado negativo para el grupo, asegurando la integridad contable de los datos.

Escenarios (Criterios BRIEF)
Los escenarios fueron redactados siguiendo los principios BRIEF para garantizar que sean orientados al negocio, esenciales y enfocados:

B (Business): Orientados a la lógica de saldos del grupo.

R (Real data): Uso de montos y descripciones realistas ("Viaje a Bariloche", "Asado").

I (Intention revealing): Los pasos clarifican la intención de validar el total.

E (Essential): Se eliminó cualquier detalle técnico innecesario en el Gherkin.

F (Focused): Cada escenario ilustra una única validación de la regla.

Gherkin (src/test/resources/features/total_grupo_no_negativo.feature)
Gherkin

# language: es
Característica: Validación del total del grupo

  Escenario: Un grupo nuevo comienza con total en cero
    Dado que creo un grupo llamado "Viaje a Bariloche"
    Entonces el total del grupo debe ser 0 pesos

  Escenario: No se puede registrar un gasto que resulte en un total negativo
    Dado que el grupo "Asado" tiene un total de 1000 pesos
    Cuando intento registrar un gasto de -1500 pesos por "Devolución"
    Entonces se debe rechazar la operación por "Saldo negativo no permitido"
    Y el total del grupo "Asado" debe permanecer en 1000 pesos
Implementación Técnica
Se optó por implementar Fast Acceptance Tests (pruebas sobre los objetos de dominio) para garantizar la velocidad de ejecución y evitar dependencias de entorno.

Pruebas de Aceptación: Implementadas en Java utilizando Step Definitions vinculados a los escenarios Gherkin.

Lógica de Dominio: La validación se centralizó en la clase Grupo, lanzando una excepción de dominio cuando la regla es vulnerada.
las pruebas deberian poder ejecutarse usando 
./gradlew check

# Repartir

## Ambiente

 * JDK 17
 * Docker
 * Chrome

## Ejecutar pruebas

Para ejecutar todas las pruebas

```
./gradlew check --info
```

## Para levantar en el ambiente local

```
./gradlew bootRun
```
## Acceso web

http://localhost:8080/

## Open API

http://localhost:8080/swagger-ui.html

##
