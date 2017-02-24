package source.EntornoReal;

import source.CPU;
import source.EntornoReal.ColaProcesamiento;

import java.util.ArrayList;

/**
 * Created by lbrunori on 16/02/17.
 */
public class Servidor {

    private CPU cpu = CPU.getInstancia();
    private ColaProcesamiento cola = ColaProcesamiento.getInstancia();
    private static Servidor servidor = null;

    private Servidor(){}

    public static Servidor getInstancia(){
        if (servidor == null){
            servidor = new Servidor();
        }
        return servidor;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public ColaProcesamiento getCola() {
        return cola;
    }

    public void setCola(ColaProcesamiento cola) {
        this.cola = cola;
    }

    public void asignarNuevoProceso() {
        Proceso p = cola.despacharProceso();
        if(p != null){
            cpu.asignarNuevoProceso(p);
        }
    }

    public boolean getEstaDisponible() {
        return cpu.getEstaDisponible();
    }

    public void procesar() {
        cpu.procesar();
    }
}
