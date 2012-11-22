package co.edu.unal.pesi.vista;

import co.edu.unal.pesi.modelo.Clasedatos;
import co.edu.unal.pesi.modelo.Grupos;
import co.edu.unal.pesi.modelo.Organizaciones;
import co.edu.unal.pesi.modelo.Subgrupos;
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
}
