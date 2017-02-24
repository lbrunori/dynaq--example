package test;

import org.junit.Test;
import source.Entorno;
import source.Estado;

/**
 * Created by lbrunori on 16/02/17.
 */
public class TestEntorno {

    @Test
    public void testEntornoCrearEstados(){
        Entorno e = Entorno.getInstancia();

        e.crearEstados(2);
        e.imprimirEstados();
    }

    @Test
    public void testEntornoCrearAcciones(){
        Entorno e = Entorno.getInstancia();
        e.crearAcciones();
        e.imprimirAcciones();
    }

    @Test
    public void testEntornoCrearTransiciones(){
        Entorno e = Entorno.getInstancia();
        e.crearEstados(3);
        e.crearAcciones();
        e.crearTransiciones();
        e.imprimirTransiciones();
    }
}
