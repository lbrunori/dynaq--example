package source;

import source.EntornoReal.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by lbrunorig50 on 07/12/16.
 */
public class Agente {

    private double[][] matrizQ;
    private double[][][] modelo;
    private int[][] matrizN;
    private boolean[][] accionesTomasEnEstado;
    private ArrayList<Estado> estadosPreviamenteObservados = new ArrayList<>();
    private Servidor servidor;
    private Entorno entorno;
    private Estado estadoActual;
    private Politica politica;
    private int cantidadSteps;

    public Agente(Politica politica) {
        this.entorno = Entorno.getInstancia();
        this.estadoActual = this.entorno.getArrayEstados().get(0);
        this.servidor = Servidor.getInstancia();
        this.politica = politica;
        this.crearMatrizQ();
        this.crearModelo();
        this.crearMatrizN();
        this.crearMatrizAccionesTomasEnEstado();
    }

    public void crearMatrizAccionesTomasEnEstado(){
        this.accionesTomasEnEstado = new boolean[entorno.getArrayEstados().size()][entorno.getArrayAcciones().size()];
    }

    public void crearMatrizQ() {
        this.matrizQ = new double[entorno.getArrayEstados().size()][entorno.getArrayAcciones().size()];
    }

    public void crearModelo() {
        //la posicion [*][*][0] es para el estado
        //la posicion [*][*][1] es para el reward
        this.modelo = new double[entorno.getArrayEstados().size()][entorno.getArrayAcciones().size()][2];
    }

    public void crearMatrizN() {
        this.matrizN = new int[entorno.getArrayEstados().size()][entorno.getArrayAcciones().size()];
    }

    public void aprender(double gamma, double alfa, int N, double k, ArrayList<Proceso> procesosEntrantes, double unidadesDeTiempo) {

        this.actualizarEstadoAgente();
        Estado s = this.getEstadoActual();
        Accion a = this.politica.obtenerAccion(procesosEntrantes, entorno.getArrayAcciones(), matrizQ, s);
        double reward = this.entorno.getReward(estadoActual, a);
        Estado s_prima = this.entorno.getEstadoSiguiente(estadoActual, a);
        Accion a_prima = this.politica.obtenerMejorAccion(s_prima, matrizQ, entorno.getArrayAcciones());

        if (a.getEsAceptar() && servidor.getCola().estaDisponible()) {
            /*System.out.println("Es aceptar " + a.getEsAceptar());
            System.out.println("Cant procesos en cola " + servidor.getCola().cantidadDeProcesosActuales());
            System.out.println("Acepto");
            System.out.println(s);
            System.out.println(s_prima);
            System.out.println(reward);*/
            this.agregarProcesoAlServidor(a, procesosEntrantes);
            Impresor.acumularReward(reward, unidadesDeTiempo);
        } else {
            AmbienteOperativo.getInstancia().aumentarTiempoDeArriboProcesos(a,procesosEntrantes);
        }


        matrizQ[s.getNumero()][a.getNumero()] = matrizQ[s.getNumero()][a.getNumero()] +
                alfa * (reward + gamma * matrizQ[s_prima.getNumero()][a_prima.getNumero()] - matrizQ[s.getNumero()][a.getNumero()]);

        modelo[s.getNumero()][a.getNumero()][0] = s_prima.getNumero();
        modelo[s.getNumero()][a.getNumero()][1] = reward;

        if (this.estadosPreviamenteObservados.indexOf(s) == -1) {
            this.estadosPreviamenteObservados.add(s);
        }

        this.accionesTomasEnEstado[s.getNumero()][a.getNumero()] = true;

        for (int i = 0; i < N; i++) {
            Estado s_modelo = estadosPreviamenteObservados.get(new Random().nextInt(estadosPreviamenteObservados.size()));
            Accion a_modelo ;
            ArrayList<Accion> auxiliarAcciones = new ArrayList<>();
            for (int j = 0; j < entorno.getArrayAcciones().size(); j++){
                if(accionesTomasEnEstado[s_modelo.getNumero()][j] == true){
                    auxiliarAcciones.add(entorno.getArrayAcciones().get(j));
                }
            }
            a_modelo = auxiliarAcciones.get(new Random().nextInt(auxiliarAcciones.size()));
            Estado s_prima_modelo = entorno.getArrayEstados().get((int) modelo[s_modelo.getNumero()][a_modelo.getNumero()][0]);
            double reward_modelo = modelo[s_modelo.getNumero()][a_modelo.getNumero()][1];
            Accion a_prima_modelo = politica.obtenerMejorAccion(s_prima_modelo, matrizQ, entorno.getArrayAcciones());

            matrizQ[s_modelo.getNumero()][a_modelo.getNumero()] = matrizQ[s_modelo.getNumero()][a_modelo.getNumero()] +
                    alfa * (reward_modelo + gamma * matrizQ[s_prima_modelo.getNumero()][a_prima_modelo.getNumero()] - matrizQ[s_modelo.getNumero()][a_modelo.getNumero()]);
        }

        this.incrementarRewardMatrizQConK(k, estadoActual, a);
        /*this.setEstadoActual(s_prima);*/

    }

    public void incrementarRewardMatrizQConK(double k, Estado estadoActual, Accion a) {
        matrizN[estadoActual.getNumero()][a.getNumero()] = 0;
        for (int i = 0; i < entorno.getArrayEstados().size(); i++) {
            for (int j = 0; j < entorno.getArrayAcciones().size(); j++) {
                matrizN[i][j] =+ 1;
                modelo[i][j][1] = +k * Math.sqrt(matrizN[i][j]);
            }
        }
	    matrizN[estadoActual.getNumero()][a.getNumero()] = 0;
    }

    private void agregarProcesoAlServidor(Accion a, ArrayList<Proceso> procesosEntrantes) {
        Proceso procesoAsignado = null;
        if (a.getTipoProceso() == 1) {
            for (Proceso p : procesosEntrantes
                    ) {
                if (p instanceof ProcesoTipoUno) {
                    this.getServidor().getCola().agregarProceso(p);
                    procesoAsignado = p;
                    Impresor.aumentarCantT1Aceptadas();
                    break;
                }
            }
        }
        if (a.getTipoProceso() == 2) {
            for (Proceso p : procesosEntrantes
                    ) {
                if (p instanceof ProcesoTipoDos) {
                    this.getServidor().getCola().agregarProceso(p);
                    procesoAsignado = p;
                    Impresor.aumentarCantT2Aceptadas();
                    break;
                }
            }
        }
        if (a.getTipoProceso() == 3) {
            for (Proceso p : procesosEntrantes
                    ) {
                if (p instanceof ProcesoTipoTres) {
                    this.getServidor().getCola().agregarProceso(p);
                    procesoAsignado = p;
                    Impresor.aumentarCantT3Aceptadas();
                    break;
                }
            }
        }
        if (a.getTipoProceso() == 4) {
            for (Proceso p : procesosEntrantes
                    ) {
                if (p instanceof ProcesoTipoCuatro) {
                    this.getServidor().getCola().agregarProceso(p);
                    procesoAsignado = p;
                    Impresor.aumentarCantT4Aceptadas();
                    break;
                }
            }
        }
        if (a.getTipoProceso() == 5) {
            for (Proceso p : procesosEntrantes
                    ) {
                if (p instanceof ProcesoTipoCinco) {
                    this.getServidor().getCola().agregarProceso(p);
                    procesoAsignado = p;
                    Impresor.aumentarCantT5Aceptadas();
                    break;
                }
            }
        }
        AmbienteOperativo.getInstancia().borrarProcesoEntrante(procesoAsignado);
    }

    public double[][] getMatrizQ() {
        return matrizQ;
    }

    public void setMatrizQ(double[][] matrizQ) {
        this.matrizQ = matrizQ;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Entorno getEntorno() {
        return entorno;
    }

    public void setEntorno(Entorno entorno) {
        this.entorno = entorno;
    }

    public void actualizarEstadoAgente() {
        int cantT1 = 0;
        int cantT2 = 0;
        int cantT3 = 0;
        int cantT4 = 0;
        int cantT5 = 0;
        for (Proceso p : servidor.getCola().getCola()
             ) {

            if(p instanceof ProcesoTipoUno){
                cantT1++;
            }
            if(p instanceof ProcesoTipoDos){
                cantT2++;
            }
            if(p instanceof ProcesoTipoTres){
                cantT3++;
            }
            if(p instanceof ProcesoTipoCuatro){
                cantT4++;
            }
            if(p instanceof ProcesoTipoCinco){
                cantT5++;
            }
        }
        for (Estado estado: this.entorno.getArrayEstados()
                ) {
            if(estado.getCantT1() == cantT1 && estado.getCantT2() == cantT2 &&
                    estado.getCantT3() == cantT3 && estado.getCantT4() == cantT4 &&
                    estado.getCantT5() == cantT5){
                this.setEstadoActual(estado);
                break;
            }
        }
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Estado getEstadoActual(){ return this.estadoActual;}

}
