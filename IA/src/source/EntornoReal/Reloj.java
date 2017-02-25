package source.EntornoReal;

import java.math.BigDecimal;

/**
 * Created by lbrunorig50 on 07/12/16.
 */


public class Reloj {

    //Cantidad de tiempo que se va a ejecutar la aplicación
    //Las unidades se van a manejar en milisegundos
    private BigDecimal unidadesDeTiempo;
    private static Reloj reloj = null;

    private Reloj(){
        this.unidadesDeTiempo = new BigDecimal(0);
    }

    public static Reloj getInstancia(){
        if(reloj == null){ return new Reloj();}
        return reloj;
    }

    //Reduce la cantidad de tiempo en un milisegundo
    public void incrementarReloj() {
        this.unidadesDeTiempo = this.unidadesDeTiempo.add(new BigDecimal(0.1));
    }

    //Devuelve las unidades de tiempo actual
    public double getUnidadesDeTiempo() {
        return new BigDecimal(this.unidadesDeTiempo.doubleValue()).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
