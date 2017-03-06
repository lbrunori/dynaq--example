package source.EntornoReal;

import jdistlib.Poisson;
import jdistlib.rng.MersenneTwister;

import java.math.BigDecimal;

/**
 * Created by lbrunorig50 on 07/12/16.
 */
public abstract class Proceso {

    private MersenneTwister poisson = new MersenneTwister();

    public abstract double getTiempoDeArribo();

    public abstract double getTiempoProcesamientoRestante();

    public abstract void setTiempoArribo(double tiempoArribo);

    public abstract void setTiempoProcesamientoRestante(double tiempoProcesamientoRestante);

    public abstract void descontarTiempoRestante(double tiempoDeProcesamiento);

    public double getPoisson(double lambda) {
        double L = Math.exp(-lambda);
        double p = 1.0;
        double k = 0.0;

        do {
            k = k + 0.5;
            p *= Math.random();
        } while (p > L);
        double numero = 1 / (k - 0.1);
        numero = redondear(numero * 10);
        return numero;

    }

    public double getExponencial(double media) {
        double numero = Math.log(1 - Math.random()) / (-(1 / media));
        return redondear(numero * 10);

    }

    public double redondear(double d) {
        BigDecimal bd = new BigDecimal(d);
        bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
}
