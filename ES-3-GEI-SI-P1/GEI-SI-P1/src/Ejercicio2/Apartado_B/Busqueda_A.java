package Ejercicio2.Apartado_B;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Busqueda_A implements EstrategiaBusquedaInformada {

    private int nodoscreados; // Variable donde almacenamos la cantidad de nodos creados por el problema
    private int nodosexplorados; // Variable donde almacenamos la cantidad de nodos que expandimos en un problema
    // Estas variables las inicializamos cada vez que se llama al metodo soluciona

    public Nodo[] soluciona(ProblemaBusqueda p, Heuristica h) throws Exception {
        nodoscreados = 0;
        nodosexplorados = 0;
        ArrayList<Nodo> explorados = new ArrayList<>(); // Lista de nodos que vamos explorando
        ArrayList<Nodo> sucesores; // Lista de nodos sucesores no se inicializa ya que en ella almacenaremos  la lista que nos devulve la funcion sucesores
        ComparadorNodos comparador = new ComparadorNodos(); // Comparador que usaremos para ordenar los nodos en nuestra cola con prioridad
        PriorityQueue<Nodo> frontera = new PriorityQueue<>(1,comparador); // Creamos una cola con prioridad usada para la frontera donde ordenamos los nodos segun el coste
        Nodo nodoactual = new Nodo(p.getEstadoInicial(),null,null, h.evalua(p.getEstadoInicial()),0);
        frontera.add(nodoactual);
        nodoscreados++;

        while(!p.esMeta(nodoactual.getStatus())){ //Condicion de parada de busqueda encontramos el nodo meta
            if(frontera.isEmpty()) throw new Exception("Lista de Frontera Vacía"); // Lanzamos excepcion si la frontera está vacía
            nodoactual = frontera.poll(); // Obtenemos el nodo de la cabeza de cola
            nodosexplorados++;
            explorados.add(nodoactual); // Añadimos a explorados
            sucesores = sucesores(nodoactual,p,h); //Calculamos los sucesores
            for (Nodo n: sucesores) { // Para cada sucesor miramos si lo añadimos o no a frontera
                n.setCoste(n.getCoste() + nodoactual.getCoste()); // Seteamos el coste del nodo
                n.setFunction(n.getCoste()+h.evalua(n.getStatus())); // Seteamos el coste de funcion del mismo
                if(!explorados.contains(n)){ //Miramos que el nodo no se encuentre ya en explorados
                    if(!frontera.contains(n)){//Comprobamos que el nodo no se encuentre ya pendiente de explorar
                        frontera.add(n); // En caso de no estar pendiente de explorar lo añadimos a frontera
                    }else{ // En caso de pertenecer ya a frotera miraremos si por heuristica es mejor reemplazar los nodos que tenemos por los nuevos
                        while(frontera.iterator().hasNext()){ // recorremos la frotnera hasta llegar a los nodos iguales
                            Nodo aux = frontera.iterator().next();
                            if(aux.equals(n) && aux.getCoste() < n.getCoste()){ // para los nodos iguales nos quedamos con el de menor coste de los 2
                                frontera.iterator().remove();
                                frontera.add(n);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return reconstruye_sol(nodoactual);
    }

    private Nodo[] reconstruye_sol(Nodo n) {
        ArrayList<Nodo>  solucion = new ArrayList<>();
        Nodo actual = n;
        while(actual.getFather() != null){
            solucion.add(actual);
            actual = actual.getFather();
        }
        Nodo [] solucioNodos = new Nodo[solucion.size()];
        solucion.toArray(solucioNodos);
        return solucioNodos;
    }
    /**
     * Calculamos los sucesores a partir de un nodo , un problema y una heuristica dada
     * @param n Nodo padre a partir del que crearemos los sucesores
     * @param p Problema de busqueda en el que aplicaremos los distintos casos
     * @param h Heuiristica aplicada para obtener el coste de cada sucesor
     * @return Lista de sucesores del nodo n
     * */
    private ArrayList<Nodo> sucesores(Nodo n, ProblemaBusqueda p,Heuristica h) throws Exception {
        ArrayList<Nodo> sucesores = new ArrayList<>();
        Accion[] acciones = p.acciones(n.getStatus());
        Estado es;
        for(Accion acc: acciones){
            es = p.result(n.getStatus(),acc);
            Nodo nuevo = new Nodo(es,acc,n,h.evalua(es),0);
            nodoscreados++;
            sucesores.add(nuevo);
        }
        return sucesores;
    }
    /**
     * Función para devolver el valor de nodos creados por el problema
     * */
    public int getNodosCreados() {
        return nodoscreados;
    }
    /**
     * Función para devolver el valor de nodos exoplorados por el problema
     * */
    public int getNodosExplorados() {
        return nodosexplorados;
    }



}
