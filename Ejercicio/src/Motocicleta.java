public class Motocicleta extends Vehiculo {
    private int cilindraje;

    public Motocicleta(String placa, String marca, String modelo, double tarifaDiaria, int cilindraje) {
        super(placa, marca, modelo, tarifaDiaria);
        this.cilindraje = cilindraje;
    }

    @Override
    public double calcularCosto(int dias) {
        double costo = tarifaDiaria * dias;
        if (cilindraje > 250) {
            costo += 75;
        }
        return costo;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    @Override
    public String obtenerDetalles() {
        return String.format("Motocicleta: %s %s (%s) | Cilindraje: %d cc | Tarifa: Q%.2f/día",
                marca, modelo, placa, cilindraje, tarifaDiaria);
    }
}
