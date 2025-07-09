/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroexportacion;

import Clases.Exportacion;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author cesc
 */
public class RegistroExportacion {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Exportacion> exportaciones = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("--- BIENVENIDO AL SISTEMA 'REGISTRO DE EXPORTACIÓN' ---");
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        ingresarExportacion();
                        break;
                    case 2:
                        mostrarReporteGeneral();
                        break;
                    case 3:
                        mostrarReporteAgrupado();
                        break;
                    case 4:
                        salir = true;
                        System.out.println("\nGracias por utilizar el sistema. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("\n>> Error: Opción no válida. Por favor, ingrese un número del 1 al 4. <<");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n>> Error: Debe ingresar un número válido. <<");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
            if (!salir) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n================ MENÚ PRINCIPAL ================");
        System.out.println("1. Ingresar exportación");
        System.out.println("2. Reporte general");
        System.out.println("3. Reporte agrupado");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // --- MÉTODO CORREGIDO ---
    /**
     * Gestiona el ingreso de los datos de una nueva exportación.
     * Ahora, cada dato se solicita y valida en un bucle individual,
     * no permitiendo continuar hasta que la entrada sea correcta.
     */
    public static void ingresarExportacion() {
        System.out.println("\n--- 1. Ingresar Nueva Exportación ---");
        try {
            String id;
            // Bucle para validar el ID del cliente en tiempo real
            while (true) {
                System.out.print("ID Cliente (formato 1-1111-1111): ");
                id = scanner.nextLine();
                if (id.matches("\\d{1}-\\d{4}-\\d{4}")) {
                    break; // Dato correcto, salir del bucle
                }
                System.out.println(">> Error: Formato de ID incorrecto. Intente de nuevo. <<");
            }

            String nombre;
            // Bucle para validar el nombre del cliente
            while (true) {
                System.out.print("Nombre completo (mínimo 7 caracteres): ");
                nombre = scanner.nextLine();
                if (nombre.trim().length() >= 7) { // [cite: 13]
                    break; // Dato correcto
                }
                System.out.println(">> Error: El nombre debe tener al menos 7 caracteres. Intente de nuevo. <<");
            }

            String tipoExp;
            // Bucle para validar el tipo de exportación
            while (true) {
                System.out.print("Tipo de Exportación (ECP o ECS): ");
                tipoExp = scanner.nextLine().toUpperCase();
                if (tipoExp.equals("ECP") || tipoExp.equals("ECS")) { // [cite: 14]
                    break; // Dato correcto
                }
                System.out.println(">> Error: El tipo debe ser 'ECP' o 'ECS'. Intente de nuevo. <<");
            }

            System.out.print("Zona de envío (País): ");
            String zona = scanner.nextLine();

            String servicio;
            // Bucle para validar el tipo de servicio
            while (true) {
                System.out.print("Tipo de Servicio (Barco o Avion): ");
                servicio = scanner.nextLine();
                if (servicio.equalsIgnoreCase("Barco") || servicio.equalsIgnoreCase("Avion")) {
                    break; // Dato correcto
                }
                System.out.println(">> Error: El servicio debe ser 'Barco' o 'Avion'. Intente de nuevo. <<");
            }

            double kilos;
            // Bucle para validar los kilogramos
            while (true) {
                try {
                    System.out.print("Kilogramos a embalar: ");
                    kilos = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el salto de línea
                    if (kilos > 0) {
                        break; // Dato correcto
                    } else {
                        System.out.println(">> Error: Los kilogramos deben ser un valor positivo. <<");
                    }
                } catch (InputMismatchException e) {
                    System.out.println(">> Error: Debe ingresar un número válido. Intente de nuevo. <<");
                    scanner.nextLine(); // Limpiar el buffer de entrada incorrecta
                }
            }

            // Una vez que todos los datos son válidos, se crea el objeto
            //Exportacion nuevaExportacion = new Exportacion(id, nombre, tipoExp, zona, servicio, kilos);
            // exportaciones.add(nuevaExportacion);

            // System.out.println("\n>> ¡Exportación registrada con éxito! <<");
            // System.out.printf("Costo de la exportación: $%.2f%n", nuevaExportacion.getCostoTotal());

        } catch (Exception e) {
            // Este catch es una red de seguridad para cualquier error inesperado
            System.out.println("\n>> Ha ocurrido un error inesperado durante el registro: " + e.getMessage() + " <<");
        }
    }

    public static void mostrarReporteGeneral() {
        System.out.println("\n--- 2. Reporte General de Exportaciones ---");
        if (exportaciones.isEmpty()) {
            System.out.println("No hay exportaciones registradas para mostrar.");
            return;
        }

        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("%-15s | %-25s | %-15s | %-20s | %-15s%n", "ID Cliente", "Nombre Completo", "Fecha Export.", "Zona de Envío", "Costo Total");
        System.out.println("------------------------------------------------------------------------------------------");
        
        for (Exportacion exp : exportaciones) {
            System.out.printf("%-15s | %-25s | %-15s | %-20s | $%-14.2f%n",
                exp.getIdCliente(),
                exp.getNombreCompleto(),
                exp.getFechaExportacionFormateada(),
                exp.getZonaEnvio(),
                exp.getCostoTotal());
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }

    public static void mostrarReporteAgrupado() {
        System.out.println("\n--- 3. Reporte Agrupado ---");
        if (exportaciones.isEmpty()) {
            System.out.println("No hay exportaciones registradas para mostrar.");
            return;
        }

        double costoTotalECP = 0;
        double costoTotalECS = 0;
        double totalKilos = 0;


        System.out.println("\n** Resumen de Costos por Tipo de Exportación **");
        System.out.printf("Costo total ECP (Carga Pesada): $%.2f%n", costoTotalECP);
        System.out.printf("Costo total ECS (Carga Suelta): $%.2f%n", costoTotalECS);

        System.out.println("\n** Resumen de Peso Total Embalado **");
        System.out.printf("Total de Kilogramos: %.2f Kg%n", totalKilos);
        System.out.println("Equivalencias:");
        System.out.printf("- Gramos: %.2f g%n", totalKilos * 1000);
        System.out.printf("- Libras: %.2f lb%n", totalKilos * 2.20462);
        System.out.printf("- Toneladas: %.4f t%n", totalKilos / 1000);
    }
    
}
