package Clases; // O el nombre de tu paquete

/**
 * Representa una exportación de Carga Suelta.
 * Hereda de Exportacion y añade una lógica de costo basada en los pies de la carga.
 */
public class ExportacionCargaSuelta extends Exportacion  implements java.io.Serializable  {
    private double piesCarga;

    public ExportacionCargaSuelta(String idCliente, String nombreCompleto, String zonaEnvio,
                                  String tipoServicio, double kilogramosEmbalar, double piesCarga) {
        
        // 1. Llama al constructor de la clase padre (Exportacion)
        super(idCliente, nombreCompleto, zonaEnvio, tipoServicio, kilogramosEmbalar);
        
        // 2. Inicializa el atributo propio de esta clase
        this.piesCarga = piesCarga;
        
        // 3. Calcula y asigna el costo total usando la lógica de esta clase
        this.costoTotal = this.calcularCosto();
    }

    /**
     * Implementa la lógica de cálculo de costo para Carga Suelta.
     * El costo es el precio base por kilo más un adicional si la carga supera los 18 pies.
     * @return el costo total calculado.
     */
    @Override
    public double calcularCosto() {
        double costoBasePorKilo;
        double costoAdicional = 0;

        // Se mantienen los precios base originales
        if (this.tipoServicio.equalsIgnoreCase("Avion")) {
            costoBasePorKilo = 450;
        } else { // Barco
            costoBasePorKilo = 150;
        }
        
        double costoBase = this.kilogramosEmbalar * costoBasePorKilo;

        // Se aplica un cargo adicional si la carga supera los 18 pies
        if (this.piesCarga > 18) {
            if (this.tipoServicio.equalsIgnoreCase("Avion")) {
                costoAdicional = 100; // Adicional de $100 para avión
            } else { // Barco
                costoAdicional = 50; // Adicional de $50 para barco
            }
        }
        
        return costoBase + costoAdicional;
    }

    // Getter para el nuevo atributo
    public double getPiesCarga() {
        return piesCarga;
    }
}