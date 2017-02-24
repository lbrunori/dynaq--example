package test;

import org.junit.Test;
import source.EntornoReal.Reloj;
import static org.junit.Assert.assertEquals;
/**
 * Created by lbrunori on 09/02/17.
 */

public class TestReloj {

    @Test
    public void testDecrementarReloj(){
        Reloj r = Reloj.getInstancia();
        for (int i = 0 ; i < 5000 ; i++){
            r.incrementarReloj();
            System.out.println(r.getUnidadesDeTiempo());
        }
        assertEquals(5000,(int) r.getUnidadesDeTiempo());
    }
}
