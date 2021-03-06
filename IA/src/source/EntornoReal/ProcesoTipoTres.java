package source.EntornoReal;

import java.math.BigDecimal;

/**
 * Created by lbrunorig50 on 07/12/16.
 */
public class ProcesoTipoTres extends Proceso{

    //valores de la clase obtenidos de la tabla que se utilizan para
    //calcular los valores con las distrubiciones
    private static double velocidadMediaInicialArriba = 0.18;
    private static double tiempoMedioProcesamiento = 3;
    private static String nombreDelProceso = "T3";

    private static int beneficio = 10;
    //valores del objeto calculados a partir de las distribuciones
    private BigDecimal tiempoArribo;
    private BigDecimal tiempoProcesamientoRestante;

    public ProcesoTipoTres() {
        //reemplazar con un valor dado por la distribucion de poisson
        this.tiempoArribo = new BigDecimal(this.getPoisson(ProcesoTipoTres.velocidadMediaInicialArriba));
        //reemplazar con un valor dado por la distribucion exponencial
        this.tiempoProcesamientoRestante = new BigDecimal(this.getExponencial(ProcesoTipoTres.tiempoMedioProcesamiento));

    }

    //descuenta una cantidad x de unidades de tiempo en milisegundos al tiempo de porcseamiento restante
    @Override
    public void descontarTiempoRestante(double tiempoDeProcesamiento){
        BigDecimal tiempoProcesadoResultante = this.tiempoProcesamientoRestante.subtract(new BigDecimal(tiempoDeProcesamiento));
        if(tiempoProcesadoResultante.doubleValue() < tiempoDeProcesamiento){
            this.tiempoProcesamientoRestante = new BigDecimal(0);
        }else {
            this.tiempoProcesamientoRestante = tiempoProcesadoResultante;
        }
    }

    @Override
    public double getTiempoDeArribo() {
        return this.tiempoArribo.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Override
    public double getTiempoProcesamientoRestante() {
        return this.tiempoProcesamientoRestante.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    @Override
    public String toString(){
        return "Proceso " + nombreDelProceso + " - Tiemop de arribo " + this.getTiempoDeArribo() + " - Tiempo de procesamiento restante " + this.getTiempoProcesamientoRestante();
    }

    public static String getNombreDelProceso() {
        return nombreDelProceso;
    }

    public void setTiempoArribo(double tiempoArribo) {
        this.tiempoArribo = new BigDecimal(tiempoArribo).setScale(1,BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public void setTiempoProcesamientoRestante(double tiempoProcesamientoRestante) {
        this.tiempoProcesamientoRestante = new BigDecimal(tiempoProcesamientoRestante).setScale(1,BigDecimal.ROUND_HALF_UP);
    }

    public static int getBeneficio(){
        return beneficio;
    }
}
