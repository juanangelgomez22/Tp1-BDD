public class Grupo {
    private List<Gasto> gastos = new ArrayList<>();
    
    public void agregarGasto(Gasto nuevoGasto) {
        if (this.getTotalAcumulado() + nuevoGasto.getMonto() < 0) {
            throw new DomainException("Saldo negativo no permitido");
        }
        this.gastos.add(nuevoGasto);
    }

    public int getTotalAcumulado() {
        return gastos.stream().mapToInt(Gasto::getMonto).sum();
    }
}