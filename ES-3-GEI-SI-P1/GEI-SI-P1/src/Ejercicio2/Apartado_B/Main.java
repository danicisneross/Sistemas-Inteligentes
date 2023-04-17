package Ejercicio2.Apartado_B;

import Ejercicio2.Apartado_A.ProblemaCuadradoMagico;
import es.udc.sistemasinteligentes.EstrategiaBusquedaInformada;
import es.udc.sistemasinteligentes.Heuristica;
import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

public class Main {

    public static void main(String[] args) throws Exception {
        //int [][] est= {{4, 9, 2}, {3, 5, 0}, {0, 1, 0}};
        //int [][] est= {{2, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int [][] est= {{2, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 1, 0, 0}};

        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial = new ProblemaCuadradoMagico.EstadoCuadrado(est);
        ProblemaBusqueda cuadrado = new ProblemaCuadradoMagico(estadoInicial);
        Heuristica h = new HeuristicaCuadradoMagico();
        Nodo[] solucion;
        System.out.println("Solucion Aplicando BusquedaA*:");
        EstrategiaBusquedaInformada buscador = new Busqueda_A();
        solucion = buscador.soluciona(cuadrado, h);
        Busqueda_A busqueda = (Busqueda_A) buscador;
        System.out.println("Solucion : ");
        System.out.println("Nodos expandidos: " + busqueda.getNodosExplorados());
        System.out.println("Nodos Creados: " + busqueda.getNodosCreados());
        for (Nodo n : solucion) {
            System.out.println(n);
        }
    }

}
