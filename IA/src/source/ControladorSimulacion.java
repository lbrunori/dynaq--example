package source;

import source.EntornoReal.*;

import java.util.ArrayList;

/**
 * Created by lbrunori on 18/02/17.
 */
public class ControladorSimulacion {

    private Entorno entorno = Entorno.getInstancia();
    private AmbienteOperativo ambienteOperativo = AmbienteOperativo.getInstancia();
    private Reloj reloj = Reloj.getInstancia();
    private Politica politica;
    private Agente agente;

    public void iniciarSimulacion(int tamanioCola, double tiempoSimulacion, double epsilon, double gamma, double alfa, int N, double k){
        entorno.crearEstados(tamanioCola);
        entorno.imprimirEstados();
        entorno.crearAcciones();
        entorno.imprimirAcciones();
        entorno.crearTransiciones();
        entorno.imprimirTransiciones();
        ambienteOperativo.generarProcesos(tiempoSimulacion);
        ColaProcesamiento.getInstancia().setTamanioCola(tamanioCola);
        politica = new Egreedy(0.1);
        agente = new Agente(politica);
        correrSimulacion(tiempoSimulacion, gamma, alfa, N, k);
    }

    private void correrSimulacion(double tiempoSimulacion, double gamma, double alfa, int N, double k){
        while(reloj.getUnidadesDeTiempo() < tiempoSimulacion){
            ArrayList<Proceso> procesosEntrantes =
                    ambienteOperativo.getProcesosEntrantes(reloj.getUnidadesDeTiempo());

            System.out.println("");
            System.out.println("Instante : " + reloj.getUnidadesDeTiempo());
            System.out.println("");

            //Debe verificarque si hay algun proceso actual y si su tiempo
            //restante de procesamiento es cero.

            System.out.println("Procesos entrantes: " + procesosEntrantes.toString());
            System.out.println("Estado cola: \n" + Servidor.getInstancia().getCola().toString());
            System.out.println("Estado CPU: " + Servidor.getInstancia().getCpu().toString());

            if(agente.getServidor().getEstaDisponible()){
                agente.getServidor().asignarNuevoProceso();
            }else{
                agente.getServidor().procesar();
            }

            while(!procesosEntrantes.isEmpty()){
                agente.aprender(gamma, alfa, N, k, procesosEntrantes,reloj.getUnidadesDeTiempo());
            }

            reloj.incrementarReloj();
        }

        Impresor.crearGraficosSobreProcesos();
    }
}
