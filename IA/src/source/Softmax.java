package source;

import jdistlib.Uniform;
import jdistlib.rng.MersenneTwister;
import source.EntornoReal.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lbrunorig50 on 08/12/16.
 */
public class Softmax implements Politica {


    private double epsilon;
    private MersenneTwister numeroAleatoriosParaEpsilon;
    private MersenneTwister numeroAleatoriosParaAcciones;
    private MersenneTwister numeroAleatorioSelector;
    private Random randomMejorAccion = new Random();

    public Softmax(double epsilon){
        this.epsilon = epsilon;
        this.numeroAleatoriosParaAcciones = new MersenneTwister();
        this.numeroAleatoriosParaEpsilon = new MersenneTwister();
        this.numeroAleatorioSelector = new MersenneTwister();
    }


    @Override
    public Accion obtenerAccion(ArrayList<Proceso> procesosEntrantes,
                                ArrayList<Accion> accionesTotales, double[][] matrizQ, Estado estadoActual) {
        if(Uniform.random(0,1, numeroAleatoriosParaEpsilon) > epsilon){
            return explotar(procesosEntrantes, accionesTotales, matrizQ, estadoActual);
        }else{
            return explorar(procesosEntrantes, accionesTotales);
        }
    }

    @Override
    public Accion explotar(ArrayList<Proceso> procesosEntrantes,
                           ArrayList<Accion> accionesTotales, double[][] matrizQ, Estado estadoActual) {
        ArrayList<Proceso> mejoresProcesos = new ArrayList<>();
        Proceso mejorProceso;
        ArrayList<Accion> accionesPosibles = new ArrayList<>();
        double qMaximo = -1000;

        for (Proceso p : procesosEntrantes
                ) {
            if( p instanceof ProcesoTipoUno){
                accionesPosibles.add(accionesTotales.get(0));
                accionesPosibles.add(accionesTotales.get(1));
                continue;
            }
            if( p instanceof ProcesoTipoDos){
                accionesPosibles.add(accionesTotales.get(2));
                accionesPosibles.add(accionesTotales.get(3));
                continue;
            }
            if( p instanceof ProcesoTipoTres){
                accionesPosibles.add(accionesTotales.get(4));
                accionesPosibles.add(accionesTotales.get(5));
                continue;
            }
            if( p instanceof ProcesoTipoCuatro){
                accionesPosibles.add(accionesTotales.get(6));
                accionesPosibles.add(accionesTotales.get(7));
                continue;
            }
            if( p instanceof ProcesoTipoCinco){
                accionesPosibles.add(accionesTotales.get(8));
                accionesPosibles.add(accionesTotales.get(9));
                continue;
            }
        }

        for (Accion a : accionesPosibles
                ) {
            if (matrizQ[estadoActual.getNumero()][a.getNumero()] > qMaximo){
                qMaximo = matrizQ[estadoActual.getNumero()][a.getNumero()];
            }
        }

        for (Proceso p : procesosEntrantes
                ) {
            if (p instanceof ProcesoTipoUno){
                if(matrizQ[estadoActual.getNumero()][0] == qMaximo || matrizQ[estadoActual.getNumero()][1] == qMaximo){
                    mejoresProcesos.add(p);
                    continue;
                }
            }
            if (p instanceof ProcesoTipoDos){
                if(matrizQ[estadoActual.getNumero()][2] == qMaximo || matrizQ[estadoActual.getNumero()][3] == qMaximo){
                    mejoresProcesos.add(p);
                    continue;
                }
            }
            if (p instanceof ProcesoTipoTres){
                if(matrizQ[estadoActual.getNumero()][4] == qMaximo || matrizQ[estadoActual.getNumero()][5] == qMaximo){
                    mejoresProcesos.add(p);
                    continue;
                }
            }
            if (p instanceof ProcesoTipoCuatro){
                if(matrizQ[estadoActual.getNumero()][6] == qMaximo || matrizQ[estadoActual.getNumero()][7] == qMaximo){
                    mejoresProcesos.add(p);
                    continue;
                }
            }if (p instanceof ProcesoTipoCinco){
                if(matrizQ[estadoActual.getNumero()][8] == qMaximo || matrizQ[estadoActual.getNumero()][9] == qMaximo){
                    mejoresProcesos.add(p);
                    continue;
                }
            }

        }

        mejorProceso = mejoresProcesos.get(randomMejorAccion.nextInt(mejoresProcesos.size()));

        if (mejorProceso instanceof ProcesoTipoUno){
            if(matrizQ[estadoActual.getNumero()][0] >= matrizQ[estadoActual.getNumero()][1]){
                return accionesTotales.get(0);
            }else{
                return accionesTotales.get(1);
            }
        }
        if (mejorProceso instanceof ProcesoTipoDos){
            if(matrizQ[estadoActual.getNumero()][2] >= matrizQ[estadoActual.getNumero()][3]){
                return accionesTotales.get(2);
            }else{
                return accionesTotales.get(3);
            }
        }
        if (mejorProceso instanceof ProcesoTipoTres){
            if(matrizQ[estadoActual.getNumero()][4] >= matrizQ[estadoActual.getNumero()][5]){
                return accionesTotales.get(4);
            }else{
                return accionesTotales.get(5);
            }
        }
        if (mejorProceso instanceof ProcesoTipoCuatro){
            if(matrizQ[estadoActual.getNumero()][6] >= matrizQ[estadoActual.getNumero()][7]){
                return accionesTotales.get(6);
            }else{
                return accionesTotales.get(7);
            }
        }if (mejorProceso instanceof ProcesoTipoCinco){
            if(matrizQ[estadoActual.getNumero()][8] >= matrizQ[estadoActual.getNumero()][9]){
                return accionesTotales.get(8);
            }else{
                return accionesTotales.get(9);
            }
        }
        return null;
    }

    @Override
    public Accion explorar(ArrayList<Proceso> procesosEntrantes,
                           ArrayList<Accion> accionesTotales) {
        Proceso p = procesosEntrantes.get(((int) Uniform.random(0, procesosEntrantes.size(), numeroAleatoriosParaAcciones)));
        if (p instanceof ProcesoTipoUno){
            return accionesTotales.get(0 + (int) Uniform.random(0, 1, numeroAleatorioSelector));
        }
        if (p instanceof ProcesoTipoDos){
            return accionesTotales.get(2 + (int) Uniform.random(0, 1, numeroAleatorioSelector));
        }
        if (p instanceof ProcesoTipoTres){
            return accionesTotales.get(4 + (int) Uniform.random(0, 1, numeroAleatorioSelector));
        }
        if (p instanceof ProcesoTipoCuatro){
            return accionesTotales.get(6 + (int) Uniform.random(0, 1, numeroAleatorioSelector));
        }
        if (p instanceof ProcesoTipoCinco){
            return accionesTotales.get(8 + (int) Uniform.random(0, 1, numeroAleatorioSelector));
        }
        return null;
    }

    @Override
    public Accion obtenerMejorAccion(Estado estadoActual, double[][] matrizQ, ArrayList<Accion> arrayAcciones) {
        ArrayList<Integer> indexMejoresAcciones = new ArrayList<Integer>();
        double qMejorAccion = -1.0;
        for (int i = 0 ; i < arrayAcciones.size(); i++){
            if (matrizQ[estadoActual.getNumero()][i] > qMejorAccion){
                indexMejoresAcciones = new ArrayList<Integer>();
                indexMejoresAcciones.add(i);
            }
            if (matrizQ[estadoActual.getNumero()][i] == qMejorAccion){
                indexMejoresAcciones.add(i);
            }
            qMejorAccion = matrizQ[estadoActual.getNumero()][i];
        }
        if(indexMejoresAcciones.size()==1){
            return arrayAcciones.get(indexMejoresAcciones.get(0));
        }else{
            return arrayAcciones.get(randomMejorAccion.nextInt(arrayAcciones.size()));
        }
    }
}
