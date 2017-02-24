package test;

import org.junit.Test;
import source.EntornoReal.ColaProcesamiento;
import source.EntornoReal.ProcesoTipoUno;
import source.EntornoReal.Proceso;
import static org.junit.Assert.assertEquals;

/**
 * Created by lbrunori on 10/02/17.
 */
public class TestColaProcesamiento {

    @Test
    public void testColaProcesamiento(){
        ColaProcesamiento colaProcesamiento = ColaProcesamiento.getInstancia();
        Proceso p1 = new ProcesoTipoUno();
        colaProcesamiento.agregarProceso(p1);
        System.out.println(colaProcesamiento);
        Proceso p2 = new ProcesoTipoUno();
        colaProcesamiento.agregarProceso(p2);
        System.out.println(colaProcesamiento);

        colaProcesamiento.despacharProceso();
        System.out.println(colaProcesamiento);

        colaProcesamiento.despacharProceso();
        System.out.println(colaProcesamiento);

        colaProcesamiento.despacharProceso();
        assertEquals(0, colaProcesamiento.cantidadDeProcesosActuales());

    }
}
