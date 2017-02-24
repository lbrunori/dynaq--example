package source.EntornoReal;

import java.util.LinkedList;

/**
 * Created by lbrunorig50 on 07/12/16.
 */
public class ColaProcesamiento {

    private static ColaProcesamiento c = null;
    private int tamanioCola = 0;
    LinkedList<Proceso> cola = null;

    private ColaProcesamiento() {
        this.cola = new LinkedList<>();
    }

    public void agregarProceso(Proceso p) {
        if (this.cola.size() < this.tamanioCola) {
            cola.addFirst(p);
        }
    }

    public Proceso despacharProceso() {
        if (!this.cola.isEmpty()) {
            return this.cola.removeLast();
        }
        return null;
    }

    public int cantidadDeProcesosActuales() {
        return this.cola.size();
    }

    public static ColaProcesamiento getInstancia() {
        if (c == null) {
            c = new ColaProcesamiento();
        }
        return c;
    }

    public boolean estaDisponible(){
        if(this.cantidadDeProcesosActuales() < tamanioCola){
            return true;
        }else{
            return false;
        }
    }

    public void setTamanioCola(int tamanio) {
        tamanioCola = tamanio;
    }

    public int getTamanioCola() {
        return tamanioCola;
    }

    public LinkedList<Proceso> getCola() {
        return cola;
    }

    @Override
    public String toString() {
        if (cola.size() > 0) {
            String stringEstadoDeCola = "";
            for (int i = 0; i < cola.size(); i++) {
                stringEstadoDeCola = stringEstadoDeCola + (cola.size() - i) + "° - " + cola.get(i).toString() + "\n";
            }
            return stringEstadoDeCola;
        } else {
            return "Cola vacía";
        }

    }
}
