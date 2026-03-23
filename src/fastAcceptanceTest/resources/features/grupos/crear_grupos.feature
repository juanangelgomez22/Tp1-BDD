# language: es

Característica: Crear Grupo para repartir gastos

  Regla: Los grupos están compuestos por al menos dos miembros

    Escenario: No puedo crear un grupo con un único miembro
      Cuando el usuario intenta crear un grupo indicando un único miembro
      Entonces no debería crear el grupo con un único miembro

  Regla: El total del gasto no debe ser cero ni negativo

    Escenario: No puedo crear un grupo con monto cero o negativo
      Dado un grupo existente con miembros
      Cuando el usuario intenta agregar un gasto con monto de -5
      Entonces la operación es rechazada por monto inválido
