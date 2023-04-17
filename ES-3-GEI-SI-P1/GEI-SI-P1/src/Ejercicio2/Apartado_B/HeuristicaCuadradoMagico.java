package Ejercicio2.Apartado_B;

import Ejercicio2.Apartado_A.ProblemaCuadradoMagico;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.Heuristica;

public class HeuristicaCuadradoMagico extends Heuristica {

    @Override
    public float evalua(Estado e) {
        ProblemaCuadradoMagico.EstadoCuadrado esMag = (ProblemaCuadradoMagico.EstadoCuadrado) e;
        int matriz[][] = esMag.getEstado();
        int tamMatriz = esMag.getTamMatriz();
        float count = 0;
        for (int i = 0; i < tamMatriz; i++) {
            for (int j = 0; j < tamMatriz; j++) {
                if(matriz[i][j] == 0)
                    count++;
            }
        }
        return count;
    }
}
