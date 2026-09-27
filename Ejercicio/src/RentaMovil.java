import java.util.Scanner;

public class RentaMovil {
    private Empresa empresa;
    private Scanner scanner;

    public RentaMovil() {
        this.empresa = new Empresa();
        this.scanner = new Scanner(System.in);
        inicializarFlota();
    }

    private void inicializarFlota() {
        empresa.registrarVehiculo(new Automovil("ABC-001", "Toyota", "Corolla", 200, 5, true));
        empresa.registrarVehiculo(new Automovil("ABC-002", "Honda", "Civic", 180, 5, false));
        
        empresa.registrarVehiculo(new Motocicleta("MOT-001", "Yamaha", "YZF-R3", 75, 321));
        empresa.registrarVehiculo(new Motocicleta("MOT-002", "Honda", "CB125", 50, 125));
        
        empresa.registrarVehiculo(new Camioneta("CAM-001", "Ford", "F-150", 200, 1.5));
        empresa.registrarVehiculo(new Camioneta("CAM-002", "Chevrolet", "Silverado", 250, 2.0));
    }

    public void mostrarMenu() {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n========== SISTEMA RENTAMÓVIL ==========");
            System.out.println("1. Registrar nuevo vehículo");
            System.out.println("2. Consultar flota");
            System.out.println("3. Cotizar alquiler");
            System.out.println("4. Confirmar alquiler");
            System.out.println("5. Registrar devolución");
            System.out.println("6. Reporte general");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine().trim();
            
            switch (opcion) {
                case "1":
                    registrarVehiculo();
                    break;
                case "2":
                    consultarFlota();
                    break;
                case "3":
                    cotizarAlquiler();
                    break;
                case "4":
                    confirmarAlquiler();
                    break;
                case "5":
                    registrarDevolucion();
                    break;
                case "6":
                    empresa.generarReporte();
                    break;
                case "7":
                    continuar = false;
                    System.out.println("Gracias por usar RentaMovil. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        
        scanner.close();
    }

    private void registrarVehiculo() {
        System.out.println("\n--- Registrar Nuevo Vehículo ---");
        System.out.println("Tipo de vehículo: 1-Automóvil, 2-Motocicleta, 3-Camioneta");
        System.out.print("Seleccione tipo: ");
        String tipo = scanner.nextLine().trim();
        
        System.out.print("Placa: ");
        String placa = scanner.nextLine().trim();
        
        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();
        
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine().trim();
        
        System.out.print("Tarifa diaria (Q): ");
        double tarifa;
        try {
            tarifa = Double.parseDouble(scanner.nextLine().trim());
            if (tarifa <= 0) {
                System.out.println("Error: La tarifa debe ser mayor que cero.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un valor numérico válido.");
            return;
        }
        
        Vehiculo vehiculo = null;
        
        if ("1".equals(tipo)) {
            System.out.print("Número de pasajeros: ");
            try {
                int pasajeros = Integer.parseInt(scanner.nextLine().trim());
                if (pasajeros <= 0) {
                    System.out.println("Error: El número de pasajeros debe ser mayor que cero.");
                    return;
                }
                System.out.print("¿Transmisión automática? (S/N): ");
                boolean automatica = "S".equalsIgnoreCase(scanner.nextLine().trim());
                vehiculo = new Automovil(placa, marca, modelo, tarifa, pasajeros, automatica);
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese valores válidos.");
                return;
            }
        } else if ("2".equals(tipo)) {
            System.out.print("Cilindraje (cc): ");
            try {
                int cilindraje = Integer.parseInt(scanner.nextLine().trim());
                if (cilindraje <= 0) {
                    System.out.println("Error: El cilindraje debe ser mayor que cero.");
                    return;
                }
                vehiculo = new Motocicleta(placa, marca, modelo, tarifa, cilindraje);
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico válido.");
                return;
            }
        } else if ("3".equals(tipo)) {
            System.out.print("Capacidad en toneladas: ");
            try {
                double capacidad = Double.parseDouble(scanner.nextLine().trim());
                if (capacidad <= 0) {
                    System.out.println("Error: La capacidad debe ser mayor que cero.");
                    return;
                }
                vehiculo = new Camioneta(placa, marca, modelo, tarifa, capacidad);
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico válido.");
                return;
            }
        } else {
            System.out.println("Tipo de vehículo no válido.");
            return;
        }
        
        if (vehiculo != null && empresa.registrarVehiculo(vehiculo)) {
            System.out.println("Vehículo registrado exitosamente.");
        } else {
            System.out.println("Error: No se pudo registrar el vehículo. Verifique que la placa no esté repetida.");
        }
    }

    private void consultarFlota() {
        System.out.println("\n--- Flota de Vehículos ---");
        
        for (Vehiculo v : empresa.getFlota()) {
            String estado = v.isDisponible() ? "Disponible" : "Alquilado";
            System.out.printf("%s [%s]\n", v.obtenerDetalles(), estado);
            System.out.printf("  Ingresos acumulados: Q%.2f\n", v.getIngresosAcumulados());
        }
        
        System.out.println();
    }

    private void cotizarAlquiler() {
        System.out.println("\n--- Cotizar Alquiler ---");
        System.out.print("Ingrese placa del vehículo: ");
        String placa = scanner.nextLine().trim();
        
        Vehiculo vehiculo = empresa.buscarPorPlaca(placa);
        if (vehiculo == null) {
            System.out.println("Error: Vehículo no encontrado.");
            return;
        }
        
        System.out.print("Número de días: ");
        int dias;
        try {
            dias = Integer.parseInt(scanner.nextLine().trim());
            if (dias <= 0) {
                System.out.println("Error: Los días deben ser un valor entero positivo.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un valor numérico válido.");
            return;
        }
        
        double costo = empresa.cotizarAlquiler(placa, dias);
        
        System.out.println();
        System.out.println(vehiculo.obtenerDetalles());
        System.out.printf("Costo total por %d día(s): Q%.2f\n", dias, costo);
        System.out.printf("Disponibilidad: %s\n", vehiculo.isDisponible() ? "Sí" : "No (actualmente alquilado)");
        System.out.println();
    }

    private void confirmarAlquiler() {
        System.out.println("\n--- Confirmar Alquiler ---");
        System.out.print("Ingrese placa del vehículo: ");
        String placa = scanner.nextLine().trim();
        
        Vehiculo vehiculo = empresa.buscarPorPlaca(placa);
        if (vehiculo == null) {
            System.out.println("Error: Vehículo no encontrado.");
            return;
        }
        
        if (!vehiculo.isDisponible()) {
            System.out.println("Error: El vehículo no está disponible para alquiler.");
            return;
        }
        
        System.out.print("Número de días: ");
        int dias;
        try {
            dias = Integer.parseInt(scanner.nextLine().trim());
            if (dias <= 0) {
                System.out.println("Error: Los días deben ser un valor entero positivo.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un valor numérico válido.");
            return;
        }
        
        double costo = vehiculo.calcularCosto(dias);
        
        System.out.println();
        System.out.println(vehiculo.obtenerDetalles());
        System.out.printf("Costo total: Q%.2f\n", costo);
        System.out.print("¿Confirmar alquiler? (S/N): ");
        
        if ("S".equalsIgnoreCase(scanner.nextLine().trim())) {
            if (empresa.confirmarAlquiler(placa, dias)) {
                System.out.println("Alquiler confirmado exitosamente.");
                System.out.printf("Monto cobrado: Q%.2f\n", costo);
            } else {
                System.out.println("Error: No se pudo confirmar el alquiler.");
            }
        } else {
            System.out.println("Alquiler cancelado. El vehículo permanece disponible.");
        }
        System.out.println();
    }

    private void registrarDevolucion() {
        System.out.println("\n--- Registrar Devolución ---");
        System.out.print("Ingrese placa del vehículo: ");
        String placa = scanner.nextLine().trim();
        
        Vehiculo vehiculo = empresa.buscarPorPlaca(placa);
        if (vehiculo == null) {
            System.out.println("Error: Vehículo no encontrado.");
            return;
        }
        
        if (vehiculo.isDisponible()) {
            System.out.println("Error: El vehículo ya está disponible (no tiene alquiler activo).");
            return;
        }
        
        if (empresa.registrarDevolucion(placa)) {
            System.out.println("Devolución registrada exitosamente.");
            System.out.println(vehiculo.obtenerDetalles());
            System.out.println("El vehículo está disponible para nuevo alquiler.");
        } else {
            System.out.println("Error: No se pudo registrar la devolución.");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RentaMovil app = new RentaMovil();
        app.mostrarMenu();
    }
}
