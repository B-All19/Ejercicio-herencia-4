import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private List<Vehiculo> flota;
    private List<Alquiler> alquileres;

    public Empresa() {
        this.flota = new ArrayList<>();
        this.alquileres = new ArrayList<>();
    }

    public boolean registrarVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null || vehiculo.getPlaca().isEmpty()) {
            return false;
        }
        
        for (Vehiculo v : flota) {
            if (v.getPlaca().equals(vehiculo.getPlaca())) {
                return false;
            }
        }
        
        return flota.add(vehiculo);
    }

    public Vehiculo buscarPorPlaca(String placa) {
        if (placa == null || placa.isEmpty()) {
            return null;
        }
        
        for (Vehiculo v : flota) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }

    public double cotizarAlquiler(String placa, int dias) {
        if (dias <= 0) {
            return -1;
        }
        
        Vehiculo vehiculo = buscarPorPlaca(placa);
        if (vehiculo == null) {
            return -1;
        }
        
        return vehiculo.calcularCosto(dias);
    }

    public boolean confirmarAlquiler(String placa, int dias) {
        if (dias <= 0) {
            return false;
        }
        
        Vehiculo vehiculo = buscarPorPlaca(placa);
        if (vehiculo == null || !vehiculo.isDisponible()) {
            return false;
        }
        
        double costo = vehiculo.calcularCosto(dias);
        Alquiler alquiler = new Alquiler(vehiculo, dias, costo);
        alquiler.confirmar();
        alquileres.add(alquiler);
        vehiculo.setDisponible(false);
        
        return true;
    }

    public boolean registrarDevolucion(String placa) {
        Vehiculo vehiculo = buscarPorPlaca(placa);
        if (vehiculo == null || vehiculo.isDisponible()) {
            return false;
        }
        
        vehiculo.setDisponible(true);
        return true;
    }

    public void generarReporte() {
        System.out.println("\n========== REPORTE GENERAL ==========");
        
        int totalVehiculos = flota.size();
        int disponibles = 0;
        int alquilados = 0;
        
        int automovilesDis = 0, automovileAlq = 0;
        int motocicletasDis = 0, motocicletaAlq = 0;
        int camionetasDis = 0, camionetaAlq = 0;
        
        for (Vehiculo v : flota) {
            if (v.isDisponible()) {
                disponibles++;
                if (v instanceof Automovil) automovilesDis++;
                else if (v instanceof Motocicleta) motocicletasDis++;
                else if (v instanceof Camioneta) camionetasDis++;
            } else {
                alquilados++;
                if (v instanceof Automovil) automovileAlq++;
                else if (v instanceof Motocicleta) motocicletaAlq++;
                else if (v instanceof Camioneta) camionetaAlq++;
            }
        }
        
        double ingresoTotal = 0;
        for (Vehiculo v : flota) {
            ingresoTotal += v.getIngresosAcumulados();
        }
        
        System.out.println("Total vehículos: " + totalVehiculos);
        System.out.println("Disponibles: " + disponibles);
        System.out.println("Alquilados: " + alquilados);
        System.out.println();
        System.out.println("Automóviles - Disponibles: " + automovilesDis + " | Alquilados: " + automovileAlq);
        System.out.println("Motocicletas - Disponibles: " + motocicletasDis + " | Alquilados: " + motocicletaAlq);
        System.out.println("Camionetas - Disponibles: " + camionetasDis + " | Alquilados: " + camionetaAlq);
        System.out.println();
        System.out.printf("Ingreso acumulado: Q%.2f\n", ingresoTotal);
        System.out.println("=====================================\n");
    }

    public List<Vehiculo> getFlota() {
        return new ArrayList<>(flota);
    }

    public List<Alquiler> getAlquileres() {
        return new ArrayList<>(alquileres);
    }

    public int getTotalVehiculos() {
        return flota.size();
    }

    public int getVehiculosDisponibles() {
        int count = 0;
        for (Vehiculo v : flota) {
            if (v.isDisponible()) count++;
        }
        return count;
    }

    public int getVehiculosAlquilados() {
        return flota.size() - getVehiculosDisponibles();
    }
}
