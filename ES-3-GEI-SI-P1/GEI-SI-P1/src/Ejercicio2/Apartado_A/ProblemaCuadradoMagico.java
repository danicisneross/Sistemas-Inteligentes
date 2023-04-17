package Ejercicio2.Apartado_A;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public ProblemaCuadradoMagico(EstadoCuadrado estadoInicial){
        super(estadoInicial);
    }

    public static class EstadoCuadrado extends Estado{
        private final int [][] estado;  // Matriz Estado
        private  int tamMatriz; // Tamaño de la matriz a partir de aqui podremos generar las acciones del estado

        public EstadoCuadrado(int [][] estadoInicial) throws Exception {
            if(estadoInicial.length != estadoInicial[0].length){    //mira si la matriz es cuadrada; [0] --> es la primera fila
                throw new Exception("Invalid Matrix tipe : Ragged");
            }
            this.estado = estadoInicial;
            this.tamMatriz = estadoInicial.length;
        }

        public int[][] getEstado() {
            return estado;
        }

        public int getTamMatriz() {
            return tamMatriz;
        }

        /** FUNCTION checkEquals **
         *      Recorre la matriz y comprueba si el numero puede insertarse o no
         *      en la matriz ya que no se admiten num repetidos.
         * */
        public boolean checkEquals(int accion){
            for (int i = 0; i < tamMatriz; i++) {
                for (int j = 0; j < tamMatriz; j++) {
                    if(estado[i][j] == accion)
                        return false;
                }
            }
            return true;
        }

        /** FUNCTION cloneMatrix **
         *      Devuelve una copia de la matriz (estado actual)
         * */
        private int[][] cloneMatrix(int[][] matrix, int tam){
            int [][] aux  = new int[tam][tam];
            for (int i = 0; i < tam ; i++) {
                for (int j = 0; j < tam ; j++) {
                    aux[i][j] = matrix[i][j];
                }
            }
            return aux;
        }

        /** FUNCTION addAccion **
         *      Añade un nuevo numero aplicable a la matriz y genera un nuevo estado.
         * */
        public EstadoCuadrado addAccion(int accion) throws Exception {
            int matrixAux [][] = cloneMatrix(estado, tamMatriz);
            for (int i = 0; i < this.tamMatriz; i++) {
                for (int j = 0; j < this.tamMatriz; j++) {
                    if(matrixAux[i][j] == 0){
                        matrixAux[i][j] = accion;
                        return new EstadoCuadrado(matrixAux);
                    }
                }
            }
            return new EstadoCuadrado(matrixAux);
        }

        /** FUNCTION getColumnSum **
         *      Devuelve la suma de la columna pasada por parametro
         * */
        public int getColumnSum(int col){
            int suma = 0;
            for (int i = 0; i <tamMatriz ; i++) {
                suma += estado[i][col];
            }
            return suma;
        }

        /** FUNCTION getRowSum **
         *      Devuelve la suma de la fila pasada por parametro.
         * */
        public int getRowSum(int row){
            int suma = 0;
            for (int i = 0; i <tamMatriz ; i++) {
                suma += estado[row][i];
            }
            return suma;
        }

        /** FUNCTION getDiagSum **
         *      Devuelve la suma de la diagonal.
         * */
        public int getDiagSum(){
            int suma = 0;
            for (int i = 0; i <tamMatriz ; i++) {
                suma += estado[i][i];
            }
            return suma;
        }

        /** FUNCTION getInvDiagSum **
         *      Devuelve la suma de la diagonal invertida.
         * */
        public int getInvDiagSum(){
            int suma = 0;
            int j = 0;
            for (int i = tamMatriz-1; i >= 0 ; i--) {
                suma += estado[i][j];
                j++;
            }
            return suma;
        }


        public String toString() {
            StringBuilder aux = new StringBuilder();
            for (int i = 0; i < estado.length;i++){
                aux.append("[ ");
                for (int j = 0; j < estado[i].length; j++) {
                    aux.append(estado[i][j]+" ");
                }
                aux.append("],");
            }
            return aux.toString();
        }

        @Override
        public boolean equals(Object obj) {
            boolean equals = true;
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;

            EstadoCuadrado that = (EstadoCuadrado) obj;

            if(estado.length != that.estado.length) return false;
            if(estado[0].length != that.estado[0].length) return false;

            for (int i = 0; i < estado.length; i++) {
                for (int j = 0; j < estado[i].length; j++) {
                    if(estado[i][j] != that.estado[i][j]) equals = false;
                }
            }
            return equals;
        }

        @Override
        public int hashCode() {     //PREGUNTAR SI ESTO VA BIEN??
            int result = 31* estado.hashCode();
            return result;
        }
    }

    public static class AccionCuadrado extends Accion{

        private int accion;

        public AccionCuadrado(int acc){
            this.accion = acc;
        }

        @Override
        public String toString() {
            return "Add " + accion;
        }

        /** FUNCTION esAplicable **
         *      En este caso como admitimos que una accion es cualquier tipo int la funcion solo 
         *      devolvera tue en caso de que el numero se encuentre entre 1 y n² siendo n el tam
         *      de la matriz cuadrada
         * */
        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadrado esMag = (EstadoCuadrado) es;
            int tamMatriz = esMag.getTamMatriz();
            if(accion <= 0)
                return false;
            if(accion > tamMatriz * tamMatriz)
                return false;
            return esMag.checkEquals(accion);
        }

        /**
         * Devuelve el estado resultante de aplicar la acción al estado "es"
         * @param es Estado sobre el que se aplica la acción
         * @return Estado resultante
         */
        @Override
        public Estado aplicaA(Estado es) throws Exception {
            EstadoCuadrado esMag = (EstadoCuadrado) es;
            if(esAplicable(es))
                return esMag.addAccion(accion);
            else
                return esMag;
        }
    }

    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadrado esMag = (EstadoCuadrado) es;
        int tamMatriz = esMag.getTamMatriz();
        int magicNumber = (tamMatriz * (tamMatriz*tamMatriz + 1))/2;

        for (int i = 0; i < tamMatriz; i++) {
            if(esMag.getColumnSum(i) != magicNumber || esMag.getRowSum(i) != magicNumber )
                return false;
        }

        if(esMag.getDiagSum() != magicNumber || esMag.getInvDiagSum() != magicNumber)
            return false;
        return true;
    }

    @Override
    public Accion[] acciones(Estado es) {
        ArrayList<Accion> listAcciones = new ArrayList<>();
        EstadoCuadrado esMag  = (EstadoCuadrado) es;
        int tamMatriz = esMag.getTamMatriz();
        AccionCuadrado action;
        for (int i = 1; i <= tamMatriz * tamMatriz; i++) {
            if(esMag.checkEquals(i)) {
                action = new AccionCuadrado(i);
                listAcciones.add(action);
            }
        }
        Accion [] accions = new Accion[listAcciones.size()];
        listAcciones.toArray(accions);
        return accions;
    }
}
