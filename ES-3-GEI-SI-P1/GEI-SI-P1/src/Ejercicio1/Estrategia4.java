package Ejercicio1;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Nodo> nodos = new ArrayList<>();  // Declaramos un arraylist de Nodos
        Nodo nodoactual = new Nodo(p.getEstadoInicial(),null,null, 0, 0); // Creamos el nodo inicial
        nodos.add(nodoactual); //Añadimos el nodo a la lista

        int i = 1;
        System.out.println((i++) + " - Empezando búsqueda en " + nodoactual.getStatus());

        while (!p.esMeta(nodoactual.getStatus())){ // comprobamos que el nodo no sea  meta
            System.out.println((i++) + " - " + nodoactual.getStatus() + " no es meta");
            Accion[] accionesDisponibles = p.acciones(nodoactual.getStatus()); // Obtenemos la lista de acciones aplicables al estado correspondiente
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Nodo nsc =  new Nodo(p.result(nodoactual.getStatus(), acc), acc, nodoactual, 0, 0);// Creamos un nodo nuevo para cada accion aplicada
                System.out.println((i++) + " - RESULT( " + nodoactual.getStatus() + ","+ acc + " )=" + nsc.getStatus());
                if (!nodos.contains(nsc)) {
                    nodoactual = nsc;
                    System.out.println((i++) + " - " + nsc.getStatus() + " NO explorado");
                    nodos.add(nodoactual); // añadimos el nodo a la lista de nodos
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + nodoactual.getStatus());
                    break;
                } else {
                    System.out.println((i++) + " - " + nsc.getStatus() + " ya explorado");
                }
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + nodoactual.getStatus());
        return reconstruye_sol(nodoactual);
    }

    private Nodo[] reconstruye_sol(Nodo n) {
        ArrayList<Nodo> solucion = new ArrayList<>();
        Nodo actual = n;
        while(actual.getFather() != null){
            solucion.add(actual);
            actual = actual.getFather();
        }
        Nodo[] arraysol = new Nodo[solucion.size()];
        solucion.toArray(arraysol);
        return arraysol;
    }
}