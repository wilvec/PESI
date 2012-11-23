/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.pesi;

import java.util.ArrayList;

/**
 *
 * @author Deivis
 */
public class AfinidadParAPar {

    private ArrayList matrizValoresLlenos;

    public void cambiar() {
        escribrirMatrizValores();
        System.out.println("_______________________________________");
        int numeroDeClases = 0, i, j, indiceSiguiente = 0;
        ArrayList claseValorActual, claseValorSiguiente;
        numeroDeClases = matrizValoresLlenos.size();
        for (i = 1; i < numeroDeClases; i = i + 2) {
            indiceSiguiente = i + 1;
            if (indiceSiguiente < numeroDeClases) {
                claseValorActual = (ArrayList) matrizValoresLlenos.get(i);
                claseValorSiguiente = (ArrayList) matrizValoresLlenos.get(indiceSiguiente);
                llenarValores(claseValorActual, i, claseValorSiguiente, indiceSiguiente);
            }
        }
        escribrirMatrizValores();
    }

    private void llenarValores(ArrayList claseValorActual, int indiceValorActual, ArrayList claseValorSiguiente, int indiceValorSiguiente) {
        String valorClaseActual = "", valorClaseSiguiente = "",valorNumerosActual="0",valorNumerosSiguiente="0";
        int numeroProcesos = 0, indice, nuevoIndice;
        double PCActual = 0, PCActualYSiguiente = 0;
        double afinidadActualSiguiente = 0;
        numeroProcesos = claseValorActual.size();
        for (indice = 1; indice < numeroProcesos; indice++) {
            valorClaseActual = (String) claseValorActual.get(indice);
            valorClaseSiguiente = (String) claseValorSiguiente.get(indice);
            if (valorClaseActual.equals("U") || valorClaseActual.equals("C") || valorClaseActual.equals("CU")) {
                PCActual = PCActual + 1;
                if (valorClaseSiguiente.equals("U") || valorClaseSiguiente.equals("C") || valorClaseSiguiente.equals("CU")) {
                    PCActualYSiguiente = PCActualYSiguiente + 1;
                }
            }
        }
        afinidadActualSiguiente = PCActual > 0 ? (PCActualYSiguiente / PCActual):0;
        for (nuevoIndice = 0; nuevoIndice < numeroProcesos; nuevoIndice++) {
            if (nuevoIndice == 0) {
            } else {
                valorNumerosActual=String.valueOf(afinidadActualSiguiente);
                if(valorNumerosActual.length()>4){
                    valorNumerosActual=valorNumerosActual.substring(0,4);
                }
                valorClaseActual=(String) claseValorActual.get(nuevoIndice);
                valorClaseSiguiente= (String) claseValorSiguiente.get(nuevoIndice);
                valorNumerosSiguiente=valorNumerosActual;
                if(valorClaseActual.equals("")){
                    valorNumerosActual="0";
                }else{
                    if(valorNumerosActual.equals("1.0")){
                    valorNumerosActual="1";
                }
                }
                if(valorClaseSiguiente.equals("")){
                    valorNumerosSiguiente="0";
                }else{
                    if(valorNumerosSiguiente.equals("1.0")){
                    valorNumerosSiguiente="1";
                }
                }

                claseValorActual.set(nuevoIndice, valorNumerosActual);
                claseValorSiguiente.set(nuevoIndice, valorNumerosSiguiente);
            }
        }
        matrizValoresLlenos.set(indiceValorSiguiente, claseValorSiguiente);
        matrizValoresLlenos.set(indiceValorActual, claseValorActual);

    }

    private void escribrirMatrizValores() {
        System.out.println("---------CAMBIO---------------");
        for (int k = 0; k < getMatrizValoresLlenos().size(); k++) {
            System.out.println(getMatrizValoresLlenos().get(k));
        }
    }

    /**
     * @return the matrizValores
     */
    /**
     * @param matrizValores the matrizValores to set
     */
    /**
     * @return the matrizValoresLlenos
     */
    public ArrayList getMatrizValoresLlenos() {
        return matrizValoresLlenos;
    }

    /**
     * @param matrizValoresLlenos the matrizValoresLlenos to set
     */
    public void setMatrizValoresLlenos(ArrayList matrizValoresLlenos) {
        this.matrizValoresLlenos = new ArrayList();
        ArrayList linea, linea1;
        String valor;
        for (Object object : matrizValoresLlenos) {
            linea = (ArrayList) object;
            linea1 = new ArrayList();
            for (Object object1 : linea) {
                valor = (String) object1;
                linea1.add(valor);
            }
            this.matrizValoresLlenos.add(linea1);
        }
    }
}
