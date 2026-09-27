public abstract class Vehiculo {
    protected String placa;
    protected String marca;
    protected String modelo;
    protected double tarifaDiaria;
    protected boolean disponible;
    protected double ingresosAcumulados;

    public Vehiculo(String placa, String marca, String modelo, double tarifaDiaria) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;
        this.disponible = true;
        this.ingresosAcumulados = 0.0;
    }

    public abstract double calcularCosto(int dias);

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public double getIngresosAcumulados() {
        return ingresosAcumulados;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void agregarIngreso(double monto) {
        this.ingresosAcumulados += monto;
    }

    public abstract String obtenerDetalles();
}
