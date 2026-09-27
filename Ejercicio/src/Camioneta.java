public class Camioneta extends Vehiculo {
    private double capacidadToneladas;

    public Camioneta(String placa, String marca, String modelo, double tarifaDiaria, double capacidadToneladas) {
        super(placa, marca, modelo, tarifaDiaria);
        this.capacidadToneladas = capacidadToneladas;
    }

    @Override
    public double calcularCosto(int dias) {
        double costo = tarifaDiaria * dias;
        double recargoPorCapacidad = 100 * capacidadToneladas * dias;
        costo += recargoPorCapacidad;
        return costo;
    }

    public double getCapacidadToneladas() {
        return capacidadToneladas;
    }

    @Override
    public String obtenerDetalles() {
        return String.format("Camioneta: %s %s (%s) | Capacidad: %.2f toneladas | Tarifa: Q%.2f/día",
                marca, modelo, placa, capacidadToneladas, tarifaDiaria);
    }
}
