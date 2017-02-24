package source;

/**
 * Created by lbrunori on 16/02/17.
 */
public class Transicion {

    private Estado estadoInicial;
    private Estado estadoFinal;
    private Accion accion;
    private double reward;

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Estado getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(Estado estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "Estado Inicial: "+estadoInicial+ "   Accion: "+ accion + "   Estado Final: " + estadoFinal + "   Reward: "+reward;

    }
}
