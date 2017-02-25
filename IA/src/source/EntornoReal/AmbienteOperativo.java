package source.EntornoReal;

import source.Accion;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbrunorig50 on 07/12/16.
 */
public class AmbienteOperativo {

    private ArrayList<Proceso> arrayProcesosTipoUno = new ArrayList<>();
    private ArrayList<Proceso> arrayProcesosTipoDos = new ArrayList<>();
    private ArrayList<Proceso> arrayProcesosTipoTres = new ArrayList<>();
    private ArrayList<Proceso> arrayProcesosTipoCuatro = new ArrayList<>();
    private ArrayList<Proceso> arrayProcesosTipoCinco = new ArrayList<>();
    private ArrayList<Proceso> arrayProcesosEntrantes;
    private static AmbienteOperativo ambienteOperativo = null;



    private AmbienteOperativo() {

    }

    public static AmbienteOperativo getInstancia(){
        if(ambienteOperativo == null){
            ambienteOperativo = new AmbienteOperativo();
        }
        return ambienteOperativo;
    }

    public ArrayList<Proceso> getProcesosEntrantes(double milisegundoActual){
        arrayProcesosEntrantes = new ArrayList<>();
        for(int i = 0; i < arrayProcesosTipoUno.size(); i++){
            if(arrayProcesosTipoUno.get(i).getTiempoDeArribo() == milisegundoActual){
                arrayProcesosEntrantes.add(arrayProcesosTipoUno.get(i));
            }
        }
        for(int i = 0; i < arrayProcesosTipoDos.size(); i++){
            if(arrayProcesosTipoDos.get(i).getTiempoDeArribo() == milisegundoActual){
                arrayProcesosEntrantes.add(arrayProcesosTipoDos.get(i));
            }
        }
        for(int i = 0; i < arrayProcesosTipoTres.size(); i++){
            if(arrayProcesosTipoTres.get(i).getTiempoDeArribo() == milisegundoActual){
                arrayProcesosEntrantes.add(arrayProcesosTipoTres.get(i));
            }
        }
        for(int i = 0; i < arrayProcesosTipoCuatro.size(); i++){
            if(arrayProcesosTipoCuatro.get(i).getTiempoDeArribo() == milisegundoActual){
                arrayProcesosEntrantes.add(arrayProcesosTipoCuatro.get(i));
            }
        }
        for(int i = 0; i < arrayProcesosTipoCinco.size(); i++){
            if(arrayProcesosTipoCinco.get(i).getTiempoDeArribo() == milisegundoActual){
                arrayProcesosEntrantes.add(arrayProcesosTipoCinco.get(i));
            }
        }

        return arrayProcesosEntrantes;
    }

    public void borrarProcesoEntrante(Proceso p){
        if(arrayProcesosEntrantes.indexOf(p) != -1){ arrayProcesosEntrantes.remove(p);}
    }

    public void aumentarTiempoDeArriboProcesos(Accion a, ArrayList<Proceso> procesosEntrantes){
        Proceso procesoABorrar = null;
        if (a.getTipoProceso() == 1){
            for (Proceso p: procesosEntrantes
                 ) {
                if(p instanceof ProcesoTipoUno){
                    procesoABorrar = p;
                    arrayProcesosTipoUno.remove(p);
                    break;
                }
            }
        }
        if (a.getTipoProceso() == 2){
            for (Proceso p: procesosEntrantes
                    ) {
                if(p instanceof ProcesoTipoDos){
                    procesoABorrar = p;
                    arrayProcesosTipoDos.remove(p);
                    break;
                }
            }
        }
        if (a.getTipoProceso() == 3){
            for (Proceso p: procesosEntrantes
                    ) {
                if(p instanceof ProcesoTipoTres){
                    procesoABorrar = p;
                    arrayProcesosTipoTres.remove(p);
                    break;
                }
            }
        }
        if (a.getTipoProceso() == 4){
            for (Proceso p: procesosEntrantes
                    ) {
                if(p instanceof ProcesoTipoCuatro){
                    procesoABorrar = p;
                    arrayProcesosTipoCuatro.remove(p);
                    break;
                }
            }
        }
        if (a.getTipoProceso() == 5){
            for (Proceso p: procesosEntrantes
                    ) {
                if(p instanceof ProcesoTipoCinco){
                    procesoABorrar = p;
                    arrayProcesosTipoCinco.remove(p);
                    break;
                }
            }
        }

        this.borrarProcesoEntrante(procesoABorrar);

        for (Proceso proceso : arrayProcesosTipoUno
                ) {
            proceso.setTiempoArribo(proceso.getTiempoDeArribo() * 1.01);
        }
        for (Proceso proceso : arrayProcesosTipoDos
                ) {
            proceso.setTiempoArribo(proceso.getTiempoDeArribo() * 1.01);
        }
        for (Proceso proceso : arrayProcesosTipoTres
                ) {
            proceso.setTiempoArribo(proceso.getTiempoDeArribo() * 1.01);
        }
        for (Proceso proceso : arrayProcesosTipoCuatro
                ) {
            proceso.setTiempoArribo(proceso.getTiempoDeArribo() * 1.01);
        }
        for (Proceso proceso : arrayProcesosTipoCinco
                ) {
            proceso.setTiempoArribo(proceso.getTiempoDeArribo() * 1.01);
        }

        if(a.getTipoProceso() == 1 ){ Impresor.aumentarCantT1Rechazadas();}
        if(a.getTipoProceso() == 2 ){ Impresor.aumentarCantT2Rechazadas();}
        if(a.getTipoProceso() == 3 ){ Impresor.aumentarCantT3Rechazadas();}
        if(a.getTipoProceso() == 4 ){ Impresor.aumentarCantT4Rechazadas();}
        if(a.getTipoProceso() == 5 ){ Impresor.aumentarCantT5Rechazadas();}


    }

    public void generarProcesos(double tiempoHasta){
        double acumuladorT1 = 0;
        double acumuladorT2 = 0;
        double acumuladorT3 = 0;
        double acumuladorT4 = 0;
        double acumuladorT5 = 0;

        while(acumuladorT1 < tiempoHasta){
            ProcesoTipoUno p = new ProcesoTipoUno();
            acumuladorT1 += p.getTiempoDeArribo();
            p.setTiempoArribo(acumuladorT1);
            this.arrayProcesosTipoUno.add(p);
        }

        while(acumuladorT2 < tiempoHasta){
            ProcesoTipoDos p = new ProcesoTipoDos();
            acumuladorT2 += p.getTiempoDeArribo();
            p.setTiempoArribo(acumuladorT2);
            this.arrayProcesosTipoDos.add(p);
        }

        while(acumuladorT3 < tiempoHasta){
            ProcesoTipoTres p = new ProcesoTipoTres();
            acumuladorT3 += p.getTiempoDeArribo();
            p.setTiempoArribo(acumuladorT3);
            this.arrayProcesosTipoTres.add(p);
        }

        while(acumuladorT4 < tiempoHasta){
            ProcesoTipoCuatro p = new ProcesoTipoCuatro();
            acumuladorT4 += p.getTiempoDeArribo();
            p.setTiempoArribo(acumuladorT4);
            this.arrayProcesosTipoCuatro.add(p);
        }

        while(acumuladorT5 < tiempoHasta){
            ProcesoTipoCinco p = new ProcesoTipoCinco();
            acumuladorT5 += p.getTiempoDeArribo();
            p.setTiempoArribo(acumuladorT5);
            this.arrayProcesosTipoCinco.add(p);
        }

        this.mostrarProcesosCreados();
        System.out.println();
        this.mostrarCantidadProcesosCreados();
    }

    public void mostrarProcesosCreados() {
        for (Proceso p: arrayProcesosTipoUno
             ) {
            System.out.println(p.toString());
        }
        for (Proceso p: arrayProcesosTipoDos
                ) {
            System.out.println(p.toString());
        }
        for (Proceso p: arrayProcesosTipoTres
                ) {
            System.out.println(p.toString());
        }
        for (Proceso p: arrayProcesosTipoCuatro
                ) {
            System.out.println(p.toString());
        }
        for (Proceso p: arrayProcesosTipoCinco
                ) {
            System.out.println(p.toString());
        }
    }


    public ArrayList<Proceso> getArrayProcesosTipoUno() {
        return arrayProcesosTipoUno;
    }

    public void setArrayProcesosTipoUno(ArrayList<Proceso>  arrayProcesosTipoUno) {
        this.arrayProcesosTipoUno = arrayProcesosTipoUno;
    }

    public ArrayList<Proceso> getArrayProcesosTipoDos() {
        return arrayProcesosTipoDos;
    }

    public void setArrayProcesosTipoDos(ArrayList<Proceso> arrayProcesosTipoDos) {
        this.arrayProcesosTipoDos = arrayProcesosTipoDos;
    }

    public ArrayList<Proceso> getArrayProcesosTipoTres() {
        return arrayProcesosTipoTres;
    }

    public void setArrayProcesosTipoTres(ArrayList<Proceso> arrayProcesosTipoTres) {
        this.arrayProcesosTipoTres = arrayProcesosTipoTres;
    }

    public ArrayList<Proceso> getArrayProcesosTipoCuatro() {
        return arrayProcesosTipoCuatro;
    }

    public void setArrayProcesosTipoCuatro(ArrayList<Proceso> arrayProcesosTipoCuatro) {
        this.arrayProcesosTipoCuatro = arrayProcesosTipoCuatro;
    }

    public ArrayList<Proceso> getArrayProcesosTipoCinco() {
        return arrayProcesosTipoCinco;
    }

    public void setArrayProcesosTipoCinco(ArrayList<Proceso> arrayProcesosTipoCinco) {
        this.arrayProcesosTipoCinco = arrayProcesosTipoCinco;
    }

    private void mostrarCantidadProcesosCreados(){
        System.out.println("Se crearon : " + this.getArrayProcesosTipoUno().size() + " procesos del tipo 1.");
        System.out.println("Se crearon : " + this.getArrayProcesosTipoDos().size() + " procesos del tipo 2.");
        System.out.println("Se crearon : " + this.getArrayProcesosTipoTres().size() + " procesos del tipo 3.");
        System.out.println("Se crearon : " + this.getArrayProcesosTipoCuatro().size() + " procesos del tipo 4.");
        System.out.println("Se crearon : " + this.getArrayProcesosTipoCinco().size() + " procesos del tipo 5.");
    }

}
