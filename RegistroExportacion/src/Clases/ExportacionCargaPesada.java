package Clases;
// La palabra clave 'extends' establece la herencia
public class ExportacionCargaPesada extends Exportacion {

    // Atributo adicional específico de esta clase 
    private String tipoCarga; // "Contenedor Refrigerado", "Contenedor no refrigerado", etc.

    public ExportacionCargaPesada(String idCliente, String nombreCompleto, String zonaEnvio, 
                                  String tipoServicio, double kilogramosEmbalar, String tipoCarga) {
        
        // 1. Llama al constructor de la clase padre (Exportacion) para inicializar los atributos comunes
        super(idCliente, nombreCompleto, zonaEnvio, tipoServicio, kilogramosEmbalar);
        
        // 2. Inicializa el atributo propio de esta clase
        this.tipoCarga = tipoCarga;
        
        // 3. Calcula y asigna el costo total llamando al método de esta misma clase
        this.costoTotal = this.calcularCosto();
    }

    // --- Implementación del Método Abstracto ---
    /**
     * Implementa la lógica de cálculo de costo para Carga Pesada.
     * @return el costo total.
     */
    @Override // Anotación que indica que estamos sobrescribiendo un método del padre
    public double calcularCosto() {
        double costoPorKilo = 0;
        
        // La regla de precios solo aplica para envíos en Barco 
        if (this.tipoServicio.equalsIgnoreCase("Barco")) {
            switch (this.tipoCarga) {
                case "Contenedor Refrigerado":
                    costoPorKilo = 950; // 
                    break;
                case "Contenedor no refrigerado":
                    costoPorKilo = 550; // 
                    break;
                case "Carga embalada":
                    costoPorKilo = 450; // 
                    break;
            }
        } else {
            // Si es Avión, el documento no especifica un nuevo precio.
            // Se puede asumir un precio base, como el de carga embalada.
            costoPorKilo = 450; 
        }
        
        return this.kilogramosEmbalar * costoPorKilo;
    }

    // --- Getter para el nuevo atributo ---
    public String getTipoCarga() {
        return tipoCarga;
    }
}