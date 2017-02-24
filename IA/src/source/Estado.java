package source;

/**
 * Created by lbrunori on 16/02/17.
 */
public class Estado {

    private int cantT1;
    private int cantT2;
    private int cantT3;
    private int cantT4;
    private int cantT5;

    private boolean estaColaLlena;
    private int numero;
    private String nombre;

    public Estado(int cantT1, int cantT2, int cantT3, int cantT4, int cantT5, int numeroEstado, boolean esColaLlena){
        this.setCantT1(cantT1);
        this.setCantT2(cantT2);
        this.setCantT3(cantT3);
        this.setCantT4(cantT4);
        this.setCantT5(cantT5);
        this.setNumero(numeroEstado);
        this.setEstaColaLlena(esColaLlena);
        this.setNombre("T1:" + cantT1 + " T2:" + cantT2 + " T3:" + cantT3 + " T4:" + cantT4 + " T5:" + cantT5);
    }

    public boolean isEstaColaLlena() {
        return estaColaLlena;
    }

    public void setEstaColaLlena(boolean estaColaLlena) {
        this.estaColaLlena = estaColaLlena;
    }

    public int getCantT1() {
        return cantT1;
    }

    public void setCantT1(int cantT1) {
        this.cantT1 = cantT1;
    }

    public int getCantT2() {
        return cantT2;
    }

    public void setCantT2(int cantT2) {
        this.cantT2 = cantT2;
    }

    public int getCantT3() {
        return cantT3;
    }

    public void setCantT3(int cantT3) {
        this.cantT3 = cantT3;
    }

    public int getCantT4() {
        return cantT4;
    }

    public void setCantT4(int cantT4) {
        this.cantT4 = cantT4;
    }

    public int getCantT5() {
        return cantT5;
    }

    public void setCantT5(int cantT5) {
        this.cantT5 = cantT5;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
