package test;

import jdistlib.Exponential;
import jdistlib.Poisson;
import jdistlib.Uniform;
import jdistlib.rng.MersenneTwister;
import org.junit.Test;

/**
 * Created by lbrunori on 11/02/17.
 */
public class TestJDistLib {

    @Test
    public void testJDistLib(){
        double[] numerosAleatoriosUniformes = Uniform.random(100, 0, 1, new MersenneTwister());
        for (double numero: numerosAleatoriosUniformes) {
            System.out.println(numero);
        }
    }

    @Test
    public void testJDistLibPoisson(){
        double[] numerosConDistPoisson = Poisson.random(100, 0.30, new MersenneTwister());
        for (double numero: numerosConDistPoisson
             ) {
            System.out.println(numero);
        }
    }

    @Test
    public void testJDistLibExponencial(){
        double[] numerosConDistPoisson = Exponential.random(100, 2, new MersenneTwister());
        for (double numero: numerosConDistPoisson
                ) {
            System.out.println(numero);
        }
    }
}
