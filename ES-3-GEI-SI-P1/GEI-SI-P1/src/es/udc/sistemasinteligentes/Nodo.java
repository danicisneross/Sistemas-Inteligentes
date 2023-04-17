package es.udc.sistemasinteligentes;

import java.util.Objects;

public class Nodo {

    private Estado status;
    private Accion action;
    private Nodo father;

    private float coste;
    private float function;

    public Nodo (Estado stat, Accion act, Nodo fath, float coste, float funct){
        this.status = stat;
        this.action = act;
        this.father = fath;
        this.coste = coste;
        this.function = funct;
    }

    public Estado getStatus() {
        return status;
    }

    public Accion getAction() {
        return action;
    }

    public Nodo getFather() {
        return father;
    }

    /**
     * Método de acceso al coste almacenado en el nodo devuelve un elemento tipo float
     * */
    public float getCoste() {
        return coste;
    }
    /**
     * Método de acceso a la funcion de coste del nodo devuelve un elemento de tipo float
     * */
    public float getFunction() {
        return function;
    }
    /**
     * Setter para porder modificar el coste de un nodo ya que este sew calcula dynamicamente mientras se
     * ejecuta el problema
     * */
    public void setCoste(float coste){
        this.coste = coste;
    }
    /**
     * Setter para poder modificar el valor de la funcion de coste del nodo ya que esta se calculara de forma
     * dinamica en la ejecucion del problema*/
    public void setFunction(float funcion){
        this.function = funcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        return Objects.equals(status, nodo.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        if(action == null && father == null){
            return "Nodo {" + " status = " + status.toString() + " action = Ninguno" + ", father = Ninguno }";
        } else {
            return "Nodo {" + " status = " + status.toString() + " action = " + action.toString() + ", father = " +  father.toString();
        }
    }
}
