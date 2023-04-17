package Ejercicio1;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ejemplo.ProblemaAspiradora;

public class Main {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial =
                new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                        ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaAspiradora aspiradora = new ProblemaAspiradora(estadoInicial);

        /*EstrategiaBusqueda buscador = new Estrategia4();
        Nodo[] solucion = buscador.soluciona(aspiradora);
        System.out.println("Solucion aplicando Estrategia 4: \n");
        for (Nodo n: solucion){
            System.out.println(n);
        }*/

        EstrategiaBusqueda buscador = new EstrategiaBusquedaGrafo();
        System.out.println("\nSolucion Aplicando Busqueda_Grafo: \n");
        Nodo[] solucion = buscador.soluciona(aspiradora);
        for (Nodo n: solucion) {
            System.out.println(n);
        }
    }
}
