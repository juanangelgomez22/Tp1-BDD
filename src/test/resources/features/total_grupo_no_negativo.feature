# language: es
Característica: Validación del total del grupo
  Como administrador de un grupo de gastos
  Quiero asegurar que el saldo total no sea negativo
  Para mantener la integridad contable del grupo

  Escenario: Un grupo nuevo comienza con total en cero
    Dado que creo un grupo llamado "Viaje a Bariloche"
    Entonces el total del grupo debe ser 0 pesos

  Escenario: No se puede registrar un gasto que resulte en un total negativo
    Dado que el grupo "Asado" tiene un total de 1000 pesos
    Cuando intento registrar un gasto de -1500 pesos por "Devolución"
    Entonces se debe rechazar la operación por "Saldo negativo no permitido"
    Y el total del grupo "Asado" debe permanecer en 1000 pesos