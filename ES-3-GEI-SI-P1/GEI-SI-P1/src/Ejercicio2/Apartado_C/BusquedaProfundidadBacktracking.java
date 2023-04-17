package Ejercicio2.Apartado_C;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.Stack;

public class BusquedaProfundidadBacktracking implements EstrategiaBusqueda {

//La idea es que Backtracking sea basada en arbol y no en grafo
//ahorrandonos los explorados, no haria falta un Array list de sucesores pero la frontera si y su tipo de dato es Stack
//
    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        //nodoscreados = 0;
        //nodosexplorados = 0;
        ArrayList<Nodo> explorados = new ArrayList<>();
        ArrayList<Nodo> sucesores;
        Stack<Nodo> frontera = new Stack<>();
        Nodo nodoactual = new Nodo(p.getEstadoInicial(),null,null, 0, 0);
        frontera.add(nodoactual);
        while(!p.esMeta(nodoactual.getStatus())){

            if(frontera.isEmpty()) throw new Exception("Lista de Frontera Vacía");
            nodoactual = frontera.pop();
            explorados.add(nodoactual);
            //nodosexplorados++;
            /*sucesores = sucesores(nodoactual,p);
            for (Nodo n: sucesores) {
                if(!explorados.contains(n) && !frontera.contains(n) ){
                    frontera.add(n);
                }
            }*/
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
        return  solucioNodos;
    }
    /**
     * Calculamos los sucesores a partir de un nodo , un problema y una heuristica dada
     * @param n Nodo padre a partir del que crearemos los sucesores
     * @param p Problema de busqueda en el que aplicaremos los distintos casos
     * @param h Heuiristica aplicada para obtener el coste de cada sucesor
     * @return Lista de sucesores del nodo n
     * */
    /*private ArrayList<Nodo> sucesores(Nodo n, ProblemaBusqueda p) throws Exception {
        ArrayList<Nodo> sucesores = new ArrayList<>();
        Accion[] acciones = p.acciones(n.getStatus());
        for(Accion acc: acciones){
            Nodo nuevo = new Nodo(p.result(n.getStatus(),acc),acc,n, 0, 0);
            //nodoscreados++;
            sucesores.add(nuevo);
        }
        return sucesores;
    }*/

}
