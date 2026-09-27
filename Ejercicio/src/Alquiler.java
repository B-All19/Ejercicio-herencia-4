public class Alquiler {
    private Vehiculo vehiculo;
    private int dias;
    private double costoTotal;
    private boolean confirmado;

    public Alquiler(Vehiculo vehiculo, int dias, double costoTotal) {
        this.vehiculo = vehiculo;
        this.dias = dias;
        this.costoTotal = costoTotal;
        this.confirmado = false;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public int getDias() {
        return dias;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void confirmar() {
        this.confirmado = true;
        vehiculo.agregarIngreso(costoTotal);
    }

    @Override
    public String toString() {
        return String.format("Alquiler: %s | Días: %d | Costo: Q%.2f | Confirmado: %s",
                vehiculo.getPlaca(), dias, costoTotal, confirmado);
    }
}
