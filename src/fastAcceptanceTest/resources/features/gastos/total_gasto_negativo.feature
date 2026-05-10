# language: es

Característica: Agregar gastos a los grupos existentes

  Regla: Los gastos no pueden ser negativos

    Escenario: No puedo agregar un gasto negativo a un grupo
      Dado un grupo existente con miembros
      Cuando el usuario intenta agregar un gasto con monto de -5
      Entonces la operación es rechazada por monto inválido

    Escenario: Puedo agregar un gasto positivo y el total se actualiza
      Dado un grupo existente con miembros
      Cuando intentó registrar un gasto de 4000 pesos por "Compra de sandwiches"
      Entonces el total del grupo debe ser 4000 pesos
