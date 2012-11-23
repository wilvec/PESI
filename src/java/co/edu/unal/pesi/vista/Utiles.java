package co.edu.unal.pesi.vista;

import co.edu.unal.pesi.AfinidadParAPar;
import co.edu.unal.pesi.VerificarAfinidad;
import co.edu.unal.pesi.modelo.Clasedatos;
import co.edu.unal.pesi.modelo.Grupos;
import co.edu.unal.pesi.modelo.Organizaciones;
import co.edu.unal.pesi.modelo.Procesos;
import co.edu.unal.pesi.modelo.Procesosorganizaciones;
import co.edu.unal.pesi.modelo.Subgrupos;
import co.edu.unal.pesi.modelo.Procesosclasesdatos;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author wilvec
 */
public class Utiles {

    public static Subgrupos buscarSubGrupoId(List lista, int id) {
        Subgrupos sub2 = null;
        for (java.util.Iterator it = lista.iterator(); it.hasNext();) {
            Subgrupos sub1 = (Subgrupos) it.next();
            if (sub1.getId().intValue() == id) {
                sub2 = sub1;
            }
        }
        return sub2;
    }

    public static Grupos buscarGrupoId(List lista, int id) {
        Grupos sub2 = null;
        for (java.util.Iterator it = lista.iterator(); it.hasNext();) {
            Grupos sub1 = (Grupos) it.next();
            if (sub1.getId().intValue() == id) {
                sub2 = sub1;
            }
        }
        return sub2;
    }

    public static Organizaciones buscarOrganizacionesId(List lista, int id) {
        Organizaciones sub2 = null;
        for (java.util.Iterator it = lista.iterator(); it.hasNext();) {
            Organizaciones sub1 = (Organizaciones) it.next();
            if (sub1.getId().intValue() == id) {
                sub2 = sub1;
            }
        }
        return sub2;
    }
    
    public static Clasedatos buscarClasedatosId(List lista, int id) {
        Clasedatos sub2 = null;
        for (java.util.Iterator it = lista.iterator(); it.hasNext();) {
            Clasedatos sub1 = (Clasedatos) it.next();
            if (sub1.getId().intValue() == id) {
                sub2 = sub1;
            }
        }
        return sub2;
    }
    
    public static Procesos buscarProcesosId(List lista, int id) {
        Procesos sub2 = null;
        for (java.util.Iterator it = lista.iterator(); it.hasNext();) {
            Procesos sub1 = (Procesos) it.next();
            if (sub1.getId().intValue() == id) {
                sub2 = sub1;
            }
        }
        return sub2;
    }
    
    public static String buscarResposabilidad(List lspo, Procesos p, Organizaciones o){
        int ret;
        for (Iterator it = lspo.iterator(); it.hasNext();) {
            Procesosorganizaciones po = (Procesosorganizaciones)it.next();
            if(po.getProcesos().equals(p) && po.getOrganizaciones().equals(o)){
                ret =  po.getResponsabilidad();
                return ret==1 ? "RESPONSABILIDAD MAYOR. TOMADOR DE DESICION" : ret==2 ? "PARTICIPACION MAYOR" : ret==3 ? "ALGUNA PARTICIPACION" : "NINGUNA PARTICIPACION";
            }
        }
        return "NINGUNA PARTICIPACION";
    } 
    
    public static String buscarTipoUso(List lspcd, Procesos p, Clasedatos cd){
        for (Iterator it = lspcd.iterator(); it.hasNext();) {
            Procesosclasesdatos pcd = (Procesosclasesdatos)it.next();
            if(pcd.getProcesos().equals(p) && pcd.getClasedatos().equals(cd)){
                return pcd.getTipouso();
            }
        }
        return "";
    }
    
    public static List pasarAArray(List lspcd, List p, List cd){
        ArrayList matriz=new ArrayList(),linea=new ArrayList();
        Clasedatos cdt;
        int i;
        linea.add("PROCESOS");
        for (Iterator it1 = p.iterator(); it1.hasNext();) {
            Procesos pr =(Procesos) it1.next();
            linea.add(pr.getNombre());
        }
        matriz.add(linea);
        for(i=0;i<cd.size();i++){
            linea=new ArrayList();
            cdt=(Clasedatos) cd.get(i);
            linea.add(cdt.getNombre());
            for (Iterator it1 = p.iterator(); it1.hasNext();) {
            Procesos pr =(Procesos) it1.next();
            linea.add(Utiles.buscarTipoUso(lspcd, pr, cdt));
        }
            matriz.add(linea);
        }
        return matriz;
        
       
    }
}
