package test;

import org.junit.Test;
import source.Agente;
import source.Egreedy;
import source.Entorno;
import source.EntornoReal.*;
import source.Politica;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
/**
 * Created by lbrunori on 10/02/17.
 */
public class TestAmbiente {

    @Test
    public void testAmbiente(){
        AmbienteOperativo ambiente = AmbienteOperativo.getInstancia();
        Entorno.getInstancia().crearEstados(2);
        Entorno.getInstancia().crearAcciones();
        Entorno.getInstancia().crearTransiciones();
        Politica egreedy = new Egreedy(0.1);
        Agente agente = new Agente(egreedy);

        Proceso p1 = new ProcesoTipoUno();
        Proceso p2 = new ProcesoTipoDos();
        Proceso p3 = new ProcesoTipoTres();
        Proceso p4 = new ProcesoTipoCuatro();
        Proceso p5 = new ProcesoTipoCinco();

        p1.setTiempoArribo(0.0);
        p2.setTiempoArribo(0.0);
        p3.setTiempoArribo(0.0);
        p4.setTiempoArribo(0.0);
        p5.setTiempoArribo(0.0);

        ArrayList<Proceso> procesosTipoUno = new ArrayList<>();
        ArrayList<Proceso> procesosTipoDos = new ArrayList<>();
        ArrayList<Proceso> procesosTipoTres = new ArrayList<>();
        ArrayList<Proceso> procesosTipoCuatro = new ArrayList<>();
        ArrayList<Proceso> procesosTipoCinco = new ArrayList<>();

        procesosTipoUno.add(p1);
        procesosTipoDos.add(p2);
        procesosTipoTres.add(p3);
        procesosTipoCuatro.add(p4);
        procesosTipoCinco.add(p5);

        ambiente.setArrayProcesosTipoUno(procesosTipoUno);
        /*ambiente.setArrayProcesosTipoDos(procesosTipoDos);
        ambiente.setArrayProcesosTipoTres(procesosTipoTres);
        ambiente.setArrayProcesosTipoCuatro(procesosTipoCuatro);
        ambiente.setArrayProcesosTipoCinco(procesosTipoCinco);*/

        ArrayList<Proceso> proceosEntrantes = ambiente.getProcesosEntrantes(0.0);

        while(!proceosEntrantes.isEmpty()){
            agente.aprender(0.75, 0.95, 10, 0.25, proceosEntrantes,0.0);
        }
    }

    @Test
    public void testNumerosPoisson(){
        AmbienteOperativo a = AmbienteOperativo.getInstancia();

        a.generarProcesos(1600);

        for (Proceso p : a.getArrayProcesosTipoUno()
                ) {
            System.out.println(p.toString());
        }

        ProcesoTipoUno proceso = new ProcesoTipoUno();
        //a.aumentarTiempoDeArriboProcesos(proceso);

        for (Proceso p : a.getArrayProcesosTipoUno()
                ) {
            System.out.println(p.toString());
        }

    }
}
