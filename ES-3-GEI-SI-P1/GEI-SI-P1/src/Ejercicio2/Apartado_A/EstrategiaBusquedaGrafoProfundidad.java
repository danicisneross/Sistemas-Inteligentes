package Ejercicio2.Apartado_A;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Stack;

public class EstrategiaBusquedaGrafoProfundidad implements EstrategiaBusqueda {

    private int nodoscreados;
    private int nodosexplorados;

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        nodoscreados = 0;
        nodosexplorados = 0;
        ArrayList<Nodo> explorados = new ArrayList<>();
        ArrayList<Nodo> sucesores;
        Stack<Nodo> frontera = new Stack<>();
        Nodo nodoactual = new Nodo(p.getEstadoInicial(),null,null, 0, 0);
        frontera.add(nodoactual);
        while(!p.esMeta(nodoactual.getStatus())){

            if(frontera.isEmpty()) throw new Exception("Lista de Frontera Vacía");
            nodoactual = frontera.pop();
            explorados.add(nodoactual);
            nodosexplorados++;
            sucesores = sucesores(nodoactual,p);
            for (Nodo n: sucesores) {
                if(!explorados.contains(n) && !frontera.contains(n) ){
                    frontera.add(n);
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

    private ArrayList<Nodo> sucesores(Nodo n, ProblemaBusqueda p) throws Exception {
        ArrayList<Nodo> sucesores = new ArrayList<>();
        Accion[] acciones = p.acciones(n.getStatus());
        for(Accion acc: acciones){
            Nodo nuevo = new Nodo(p.result(n.getStatus(),acc),acc,n, 0, 0);
            nodoscreados++;
            sucesores.add(nuevo);
        }
        return sucesores;
    }

    public int getNodosCreados() {
        return nodoscreados;
    }

    public int getNodosExplorados() {
        return nodosexplorados;
    }
}
