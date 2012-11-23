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
public class VerificarAfinidad {
    public VerificarAfinidad(){
        
    }

    private ArrayList matriz;
    private double afinidadGeneral=-1;

    public void verificar() {
        escribrirMatriz();
        System.out.println("_______________________________________");
        int numeroDeClases = 0, i, j,indiceSiguiente=0,segundo=0;
        ArrayList claseActual, claseSiguiente, claseSubSiguiente;
        numeroDeClases = matriz.size();
        for (i = 1; i < numeroDeClases; i=i+2) {
            indiceSiguiente=i+1;
            if(indiceSiguiente<numeroDeClases){
            claseActual = (ArrayList) matriz.get(i);
            claseSiguiente = (ArrayList) matriz.get(indiceSiguiente);
            for (j = i + 2; j < numeroDeClases; j++) {
                claseSubSiguiente = (ArrayList) matriz.get(j);
                claseSiguiente = (ArrayList) matriz.get(indiceSiguiente);
                if (prioridadMayor(claseActual, claseSiguiente, claseSubSiguiente)) {
                    cambiarOrdenClases(indiceSiguiente,claseSiguiente,j,claseSubSiguiente);
                    segundo=1;
                }
            }
//            /////////////////////////////////////
//            if(segundo==0 && afinidadGeneral==0){
//                cambiarOrdenClases(i,claseActual,indiceSiguiente,claseSiguiente);
//                for (j = i + 2; j < numeroDeClases; j++) {
//                claseSubSiguiente = (ArrayList) matriz.get(j);
//                claseSiguiente = (ArrayList) matriz.get(indiceSiguiente);
//                if (prioridadMayor(claseActual, claseSiguiente, claseSubSiguiente)) {
//                    cambiarOrdenClases(indiceSiguiente,claseSiguiente,j,claseSubSiguiente);
//                }
//            }
//            }
//            ////////////////////////////////////////////
        }
        }
        escribrirMatriz();
    }

    

    private boolean prioridadMayor(ArrayList claseActual, ArrayList claseSiguiente, ArrayList claseSubSiguiente) {
        String valorClaseActual = "", valorClaseSiguiente = "", valorClaseSubSiguiente = "";
        int numeroProcesos = 0, indice;
        double PCActual = 0, PCActualYSiguiente = 0, PCActualYSubSiguiente = 0;
        double afinidadActualSiguiente = 0, afinidadActualSubSiguiente = 0;
        numeroProcesos = claseActual.size();
        for (indice = 1; indice < numeroProcesos; indice++) {
            valorClaseActual = (String) claseActual.get(indice);
            valorClaseSiguiente = (String) claseSiguiente.get(indice);
            valorClaseSubSiguiente = (String) claseSubSiguiente.get(indice);
            if (valorClaseActual.equals("U") || valorClaseActual.equals("C")) {
                PCActual = PCActual + 1;
                if (valorClaseSiguiente.equals("U") || valorClaseSiguiente.equals("C")) {
                    PCActualYSiguiente = PCActualYSiguiente + 1;
                }
                if (valorClaseSubSiguiente.equals("U") || valorClaseSubSiguiente.equals("C")) {
                    PCActualYSubSiguiente = PCActualYSubSiguiente + 1;
                }

            }
        }
        afinidadActualSiguiente = PCActualYSiguiente / PCActual;
        afinidadActualSubSiguiente = PCActualYSubSiguiente / PCActual;
        afinidadGeneral=afinidadActualSiguiente;
        if (afinidadActualSubSiguiente > afinidadActualSiguiente) {
            return true;
        } else {
            return false;
        }
    }

    

    private void cambiarOrdenClases(int indiceSiguiente,ArrayList claseSiguiente, int indiceNuevo, ArrayList claseNueva){
        matriz.set(indiceSiguiente, claseNueva);
        matriz.set(indiceNuevo, claseSiguiente);
    }
    private void escribrirMatriz(){
        for(int k=0;k<matriz.size();k++){
        System.out.println(matriz.get(k));
        }
    }
    

    /**
     * @return the matriz
     */
    public ArrayList getMatriz() {
        return matriz;
    }

    /**
     * @param matriz the matriz to set
     */
    public void setMatriz(ArrayList matriz) {
        this.matriz=new ArrayList();
        ArrayList linea,linea1;
        String valor;
        for (Object object : matriz) {
            linea=(ArrayList) object;
            linea1=new ArrayList();
            for (Object object1 : linea) {
                valor=   (String) object1;
                linea1.add(valor);
            }
            this.matriz.add(linea1);
        }
    }
    
}
