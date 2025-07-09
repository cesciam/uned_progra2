package Clases;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * CLASE ABSTRACTA que sirve como base para todos los tipos de exportación.
 * Define los atributos y métodos comunes, y declara los métodos que deben ser
 * implementados por sus clases hijas.
 */
public abstract class Exportacion {

    protected String idCliente;
    protected String nombreCompleto;
    protected LocalDate fechaExportacion;
    protected String zonaEnvio;
    protected String tipoServicio; // "Barco" o "Avion"
    protected double kilogramosEmbalar;
    protected double costoTotal;


    // --- Constructor ---

    /**
     * Construye un nuevo objeto de tipo Exportacion.
     *
     * @param idCliente Cédula del cliente (formato X-XXXX-XXXX).
     * @param nombreCompleto Nombre del cliente (mínimo 7 caracteres).
     * @param zonaEnvio País de destino.
     * @param tipoServicio Medio de transporte ("Barco" o "Avion").
     * @param kilogramosEmbalar Peso en Kg de la mercancía.
     */
    public Exportacion(String idCliente, String nombreCompleto, String zonaEnvio, 
                       String tipoServicio, double kilogramosEmbalar) {
        
        // Se asignan los valores utilizando los setters para aplicar validaciones
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
        this.zonaEnvio = zonaEnvio;
        this.tipoServicio = tipoServicio;
        this.kilogramosEmbalar = kilogramosEmbalar;
        this.fechaExportacion = LocalDate.now();
        
        // La fecha se asigna automáticamente con la fecha actual del sistema
        this.fechaExportacion = LocalDate.now();
    }

    // --- Métodos Getters y Setters con Validaciones ---

    public String getIdCliente() {
        return idCliente;
    }

    /**
     * Asigna el ID del cliente.
     * Valida que el formato sea el correcto (ej: 1-1111-1111).
     * @param idCliente El ID a validar y asignar.
     */
    public final void setIdCliente(String idCliente) {
        // Validación simple de formato. Una validación más robusta usaría Regex.
        if (idCliente == null || !idCliente.matches("\\d{1}-\\d{4}-\\d{4}")) {
            throw new IllegalArgumentException("Formato de ID de cliente incorrecto. Debe ser X-XXXX-XXXX.");
        }
        this.idCliente = idCliente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Asigna el nombre completo del cliente.
     * Valida que la longitud sea de al menos 7 caracteres.
     * @param nombreCompleto El nombre a validar y asignar.
     */
    public final void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.trim().length() < 7) {
            throw new IllegalArgumentException("El nombre completo debe tener al menos 7 caracteres.");
        }
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Devuelve la fecha de exportación formateada como "dd/MM/yyyy".
     * @return La fecha en formato de texto.
     */
    public String getFechaExportacionFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaExportacion.format(formatter);
    }
    
    public LocalDate getFechaExportacion() {
        return fechaExportacion;
    }

    public String getZonaEnvio() {
        return zonaEnvio;
    }

    public final void setZonaEnvio(String zonaEnvio) {
        if (zonaEnvio == null || zonaEnvio.trim().isEmpty()) {
            throw new IllegalArgumentException("La zona de envío no puede estar vacía.");
        }
        this.zonaEnvio = zonaEnvio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    /**
     * Asigna el tipo de servicio.
     * Valida que sea "Barco" o "Avion".
     * @param tipoServicio El servicio a validar y asignar.
     */
    public final void setTipoServicio(String tipoServicio) {
        if (tipoServicio == null || (!tipoServicio.equalsIgnoreCase("Barco") && !tipoServicio.equalsIgnoreCase("Avion"))) {
            throw new IllegalArgumentException("El tipo de servicio es incorrecto. Debe ser 'Barco' o 'Avion'.");
        }
        this.tipoServicio = tipoServicio;
    }

    public double getKilogramosEmbalar() {
        return kilogramosEmbalar;
    }

    /**
     * Asigna los kilogramos.
     * Valida que el valor sea positivo.
     * @param kilogramosEmbalar El peso en Kg.
     */
    public final void setKilogramosEmbalar(double kilogramosEmbalar) {
        if (kilogramosEmbalar <= 0) {
            throw new IllegalArgumentException("Los kilogramos a embalar deben ser un valor positivo.");
        }
        this.kilogramosEmbalar = kilogramosEmbalar;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    // --- Métodos de Lógica de Negocio ---
    
    /**
     * Calcula el costo total de la exportación basado en el peso y el tipo de servicio.
     * Los costos son $450/kg en avión y $150/kg en barco.
     * Este método es privado porque su invocación es una lógica interna de la clase.
     * @return El costo total calculado.
     */
    public abstract double calcularCosto(); 

    /**
     * Realiza la conversión de los kilogramos de esta exportación a gramos.
     * @return El valor equivalente en gramos.
     */
    public double convertirKgAGramos() {
        return this.kilogramosEmbalar * 1000; //
    }

    /**
     * Realiza la conversión de los kilogramos de esta exportación a libras.
     * @return El valor equivalente en libras.
     */
    public double convertirKgALibras() {
        return this.kilogramosEmbalar * 2.20462; //
    }

    /**
     * Realiza la conversión de los kilogramos de esta exportación a toneladas.
     * @return El valor equivalente en toneladas.
     */
    public double convertirKgAToneladas() {
        return this.kilogramosEmbalar / 1000; //
    }
    
    
    // --- Método toString para Representación de Texto ---
    
    /**
     * Devuelve una representación en formato de texto del objeto Exportacion.
     * Útil para depuración y visualización rápida.
     * @return Una cadena con los datos de la exportación.
     */
    @Override
    public String toString() {
        return "Exportacion{" +
                "idCliente='" + idCliente + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fechaExportacion=" + getFechaExportacionFormateada() +
                ", zonaEnvio='" + zonaEnvio + '\'' +
                ", tipoServicio='" + tipoServicio + '\'' +
                ", kilogramosEmbalar=" + kilogramosEmbalar +
                ", costoTotal=" + costoTotal +
                '}';
    }
}