package source;

import source.EntornoReal.Proceso;

import java.util.ArrayList;

/**
 * Created by lbrunorig50 on 07/12/16.
 */
public interface Politica {

    public Accion obtenerAccion(ArrayList<Proceso> procesosEntrantes,
                                ArrayList<Accion> accionesTotales,
                                double[][] matrizQ, Estado estadoActual);

    public Accion explotar(ArrayList<Proceso> procesosEntrantes,
                           ArrayList<Accion> accionesTotales,
                           double[][] matrizQ, Estado estadoActual);

    public Accion explorar(ArrayList<Proceso> procesosEntrantes,
                           ArrayList<Accion> accionesTotales);

    public Accion obtenerMejorAccion(Estado estadoActual, double[][] matrizQ, ArrayList<Accion> arrayAcciones);

}
