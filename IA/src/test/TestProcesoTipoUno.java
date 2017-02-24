package test;

import org.junit.Test;
import source.EntornoReal.Proceso;
import source.EntornoReal.ProcesoTipoDos;
import source.EntornoReal.ProcesoTipoTres;
import source.EntornoReal.ProcesoTipoUno;
import static org.junit.Assert.assertEquals;

/**
 * Created by lbrunori on 09/02/17.
 */
public class TestProcesoTipoUno {

    @Test
    public void testProcesoTipoUno(){
        ProcesoTipoUno ptu = new ProcesoTipoUno();
        System.out.println(ptu.getTiempoDeArribo());
        for(int i = 0; i < 60 ; i++){
            ptu.descontarTiempoRestante(7);
            System.out.println(ptu.getTiempoProcesamientoRestante());
        }
        assertEquals(0, ptu.getTiempoProcesamientoRestante());
    }

    @Test
    public void testProceso(){
        Proceso p1 = new ProcesoTipoUno();
        System.out.println(p1.getTiempoProcesamientoRestante());
        System.out.println(p1.getTiempoDeArribo());

        Proceso p2 = new ProcesoTipoDos();
        System.out.println(p2.getTiempoProcesamientoRestante());
        System.out.println(p2.getTiempoDeArribo());

        Proceso p3 = new ProcesoTipoTres();
        System.out.println(p3.getTiempoProcesamientoRestante());
        System.out.println(p3.getTiempoDeArribo());

        Proceso p4 = new ProcesoTipoUno();
        System.out.println(p4.getTiempoProcesamientoRestante());
        System.out.println(p4.getTiempoDeArribo());

        Proceso p5 = new ProcesoTipoUno();
        System.out.println(p5.getTiempoProcesamientoRestante());
        System.out.println(p5.getTiempoDeArribo());



    }
}
