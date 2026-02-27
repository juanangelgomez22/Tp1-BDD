# language: es

Característica: Crear Grupo para repartir gastos

  Regla: Los grupos están compuestos por al menos dos miembros

    Escenario: No puedo crear un grupo con un único miembro
      Cuando el usuario intenta crear un grupo indicando un único miembro
      Entonces no debería crear el grupo con un único miembro

Característica: Crear Grupo para repartir gastos

  Regla: El total del gasto no debe ser cero ni negativo

    Escenario: No puedo crear un grupo con precio negativo de comsimision
      Cuando el usuario intenta crear un grupo indicando que alguno pone importe cero o negativo
      Entonces no debería crear el grupo
