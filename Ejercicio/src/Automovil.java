public class Automovil extends Vehiculo {
    private int numPasajeros;
    private boolean transmisionAutomatica;

    public Automovil(String placa, String marca, String modelo, double tarifaDiaria, 
                     int numPasajeros, boolean transmisionAutomatica) {
        super(placa, marca, modelo, tarifaDiaria);
        this.numPasajeros = numPasajeros;
        this.transmisionAutomatica = transmisionAutomatica;
    }

    @Override
    public double calcularCosto(int dias) {
        double costo = tarifaDiaria * dias;
        if (transmisionAutomatica) {
            costo += 50 * dias;
        }
        return costo;
    }

    public int getNumPasajeros() {
        return numPasajeros;
    }

    public boolean isTransmisionAutomatica() {
        return transmisionAutomatica;
    }

    @Override
    public String obtenerDetalles() {
        String transmision = transmisionAutomatica ? "Automática" : "Manual";
        return String.format("Automóvil: %s %s (%s) | Pasajeros: %d | Transmisión: %s | Tarifa: Q%.2f/día",
                marca, modelo, placa, numPasajeros, transmision, tarifaDiaria);
    }
}
