package Ejercicio2.Apartado_A;

import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

public class Main {

    public static void main(String[] args) throws Exception {

        /** Descomenta el estado inicial que quieras usar*/
        //int[][] est = {{4, 9, 2}, {3, 5, 0},{0, 1, 0}};
        //int [][] est = {{2,0,0},{0,0,0},{0,0,0}};
        int [][] est = {{2,0,0,0},{0,0,0,0},{0,0,0,0},{0,1,0,0}};

        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial  = new ProblemaCuadradoMagico.EstadoCuadrado(est);
        ProblemaBusqueda cuadrado = new ProblemaCuadradoMagico(estadoInicial);
        Nodo[] solucion;
        EstrategiaBusquedaGrafoProfundidad buscador;

        System.out.println("\nSolucion Aplicando BusquedaProfundiad:");
        buscador = new EstrategiaBusquedaGrafoProfundidad();
        solucion = buscador.soluciona(cuadrado);
        EstrategiaBusquedaGrafoProfundidad busqueda = buscador;
        System.out.println("Solucion : ");
        System.out.println("Nodos Explorados: "+ busqueda.getNodosExplorados());
        System.out.println("Nodos Creados: "+ busqueda.getNodosCreados());
        for (Nodo n: solucion) {
            System.out.println(n);
        }
    }
}
