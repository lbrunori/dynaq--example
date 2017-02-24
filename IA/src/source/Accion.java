package source;

/**
 * Created by lbrunori on 16/02/17.
 */
public class Accion {

    private String nombre;
    private boolean esAceptar;
    private int tipoProceso;
    private int numero;

    public Accion(String nombre, boolean esAceptar, int tipoProceso, int numero) {
        this.nombre = nombre;
        this.esAceptar = esAceptar;
        this.tipoProceso = tipoProceso;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEsAceptar() {
        return esAceptar;
    }

    public void setEsAceptar(boolean esAceptar) {
        this.esAceptar = esAceptar;
    }

    public int getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(int tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return  nombre;
    }
}
