package source;

import source.EntornoReal.ColaProcesamiento;
import source.EntornoReal.Proceso;

/**
 * Created by lbrunori on 09/02/17.
 */
public class CPU {

    private Proceso procesoActual;
    private boolean estaDisponible;
    private static CPU cpu = null;

    private CPU(){
        this.procesoActual = null;
        this.estaDisponible = true;
    }

    public void asignarNuevoProceso(Proceso p){
        if(p != null){
            this.procesoActual = p;
            this.setEstaDisponible(false);
        }
    }

    public void procesar(){
        try{
            if(this.procesoActual != null) {
                this.procesoActual.descontarTiempoRestante(0.1);
                if (this.procesoActual.getTiempoProcesamientoRestante() == 0) {
                    this.procesoActual = null;
                    this.setEstaDisponible(true);
                }
            }
        }catch (NullPointerException e){
            throw e;
        }
    }

    public boolean getEstaDisponible(){
        return this.estaDisponible;
    }

    public void setEstaDisponible(boolean estaDisponible){
        this.estaDisponible = estaDisponible;
    }

    public static CPU getInstancia(){
        if (cpu == null){
            cpu = new CPU();
        }
        return cpu;
    }

    @Override
    public String toString(){

        String estado = "";
        if (this.estaDisponible){ estado = estado + "Disponible ";}
        if (!this.estaDisponible){ estado = estado + "Ocupado ";}
        if (!this.estaDisponible){
            estado = estado + "- " + this.procesoActual.toString();
        }
        return estado;
    }
}
