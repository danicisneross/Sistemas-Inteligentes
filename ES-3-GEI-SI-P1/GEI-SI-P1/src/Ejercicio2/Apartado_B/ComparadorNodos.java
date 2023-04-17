package Ejercicio2.Apartado_B;

import es.udc.sistemasinteligentes.Nodo;

import java.util.Comparator;

public class ComparadorNodos implements Comparator<Nodo> {

    /*
     * En este caso los nodos los ordenamos segun la funcion de coste de los mismos
     * */
    @Override
    public int compare(Nodo o1, Nodo o2) {
        if(o1.getFunction() < o2.getFunction())
            return 1;
        if(o1.getFunction() > o2.getFunction())
            return -1;
        return 0;
    }
}