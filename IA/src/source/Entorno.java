package source;

import source.EntornoReal.*;

import java.util.ArrayList;

/**
 * Created by lbrunori on 16/02/17.
 */
public class Entorno {

    private ArrayList<Accion> arrayAcciones = new ArrayList<>();
    private ArrayList<Estado> arrayEstados = new ArrayList<>();
    private ArrayList<Transicion> arrayTransicion = new ArrayList<>();
    private static Entorno entorno = null;

    private Entorno(){}

    public static Entorno getInstancia(){
        if(entorno == null){
            entorno = new Entorno();
        }
        return entorno;
    }

    public void crearEstados(int tamanioCola){
        int numeroEstado = 0;

        for(int i = 0; i <= tamanioCola; i++){
            int total;
            for(int j = 0; j <= tamanioCola; j++){
                for(int k = 0; k <= tamanioCola; k++){
                    for(int l = 0; l <= tamanioCola; l++){
                        for(int m = 0; m <= tamanioCola; m++){
                            total = (i+j+k+l+m);
                            if(total <= tamanioCola){
                                Estado e;
                                if(total == tamanioCola){
                                    e = new Estado(i,j,k,l,m, numeroEstado,true);

                                }else {
                                    e = new Estado(i,j,k,l,m, numeroEstado, false);
                                }
                                this.arrayEstados.add(e);
                                numeroEstado++;
                            }
                        }
                    }
                }
            }
        }
    }

    public void imprimirEstados(){
        for (Estado e: this.getArrayEstados()
             ) {
            System.out.println(e.toString());
        }
    }

    public void crearAcciones(){
        Accion a0 = new Accion("ACEPTAR T1",true ,1, 0);
        Accion a1 = new Accion("RECHAZAR T1", false,1, 1);
        Accion a2 = new Accion("ACEPTAR T2", true,2, 2);
        Accion a3 = new Accion("RECHAZAR T2", false,2, 3);
        Accion a4 = new Accion("ACEPTAR T3", true,3, 4);
        Accion a5 = new Accion("RECHAZAR T3", false,3,5);
        Accion a6 = new Accion("ACEPTAR T4", true,4, 6);
        Accion a7 = new Accion("RECHAZAR T4",false ,4,7 );
        Accion a8 = new Accion("ACEPTAR T5", true,5, 8);
        Accion a9 = new Accion("RECHAZAR T5", false,5, 9);
        this.getArrayAcciones().add(0, a0);
        this.getArrayAcciones().add(1,a1);
        this.getArrayAcciones().add(2,a2);
        this.getArrayAcciones().add(3,a3);
        this.getArrayAcciones().add(4,a4);
        this.getArrayAcciones().add(5,a5);
        this.getArrayAcciones().add(6,a6);
        this.getArrayAcciones().add(7,a7);
        this.getArrayAcciones().add(8,a8);
        this.getArrayAcciones().add(9,a9);
    }

    public void imprimirAcciones(){
        for (Accion a: this.getArrayAcciones()
             ) {
            System.out.println(a);
        }
    }

    public void crearTransiciones(){
        for (int i = 0 ; i < this.getArrayEstados().size(); i++){
            Estado estadoInicial = this.getArrayEstados().get(i);
            for(int j = 0; j < this.getArrayAcciones().size(); j++){
                int cantT1 = estadoInicial.getCantT1();
                int cantT2 = estadoInicial.getCantT2();
                int cantT3 = estadoInicial.getCantT3();
                int cantT4 = estadoInicial.getCantT4();
                int cantT5 = estadoInicial.getCantT5();
                Transicion transicion = new Transicion();
                transicion.setEstadoInicial(estadoInicial);
                transicion.setAccion(this.getArrayAcciones().get(j));
                if(this.getArrayAcciones().get(j).getEsAceptar() == true && this.getArrayAcciones().get(j).getTipoProceso() == 1){
                    cantT1++;
                    transicion.setReward(ProcesoTipoUno.getBeneficio());
                }
                if(this.getArrayAcciones().get(j).getEsAceptar() == true && this.getArrayAcciones().get(j).getTipoProceso() == 2){
                    cantT2++;
                    transicion.setReward(ProcesoTipoDos.getBeneficio());
                }
                if(this.getArrayAcciones().get(j).getEsAceptar() == true && this.getArrayAcciones().get(j).getTipoProceso() == 3){
                    cantT3++;
                    transicion.setReward(ProcesoTipoTres.getBeneficio());
                }
                if(this.getArrayAcciones().get(j).getEsAceptar() == true && this.getArrayAcciones().get(j).getTipoProceso() == 4){
                    cantT4++;
                    transicion.setReward(ProcesoTipoCuatro.getBeneficio());
                }
                if(this.getArrayAcciones().get(j).getEsAceptar() == true && this.getArrayAcciones().get(j).getTipoProceso() == 5){
                    cantT5++;
                    transicion.setReward(ProcesoTipoCinco.getBeneficio());
                }

                for (Estado estado: this.getArrayEstados()
                        ) {
                    if(estado.getCantT1() == cantT1 && estado.getCantT2() == cantT2 &&
                            estado.getCantT3() == cantT3 && estado.getCantT4() == cantT4 &&
                            estado.getCantT5() == cantT5){
                        transicion.setEstadoFinal(estado);
                        break;
                    }
                }
                if (transicion.getEstadoFinal() == null) {
                    transicion.setEstadoFinal(transicion.getEstadoInicial());
                    transicion.setReward(0.0);
                }
                if(transicion.getEstadoInicial().isEstaColaLlena() == true &&
                        transicion.getAccion().getEsAceptar() == true){
                    transicion.setReward(-100.0);
                }
                this.getArrayTransicion().add(transicion);
            }
        }
    }

    public void imprimirTransiciones(){
        for (Transicion transicion: this.getArrayTransicion()
             ) {
            System.out.println(transicion);
        }
    }

    public ArrayList<Accion> getArrayAcciones() {
        return arrayAcciones;
    }

    public void setArrayAcciones(ArrayList<Accion> arrayAcciones) {
        this.arrayAcciones = arrayAcciones;
    }

    public ArrayList<Estado> getArrayEstados() {
        return arrayEstados;
    }

    public void setArrayEstados(ArrayList<Estado> arrayEstados) {
        this.arrayEstados = arrayEstados;
    }

    public ArrayList<Transicion> getArrayTransicion() {
        return arrayTransicion;
    }

    public void setArrayTransicion(ArrayList<Transicion> arrayTransicion) {
        this.arrayTransicion = arrayTransicion;
    }

    public Estado getEstadoSiguiente(Estado estadoActual, Accion a) {
        for (Transicion t: arrayTransicion
             ) {
            if(t.getEstadoInicial() == estadoActual && t.getAccion() == a){
                return t.getEstadoFinal();
            }
        }
        return null;
    }

    public double getReward(Estado estadoActual, Accion a) {
        for (Transicion t: arrayTransicion
                ) {
            if(t.getEstadoInicial() == estadoActual && t.getAccion() == a){
                return t.getReward();
            }
        }
        return 0.0;
    }
}
