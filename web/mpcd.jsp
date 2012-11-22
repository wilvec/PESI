<%@page import="co.edu.unal.pesi.vista.Utiles"%>
<%@page import="co.edu.unal.pesi.modelo.*"%>
<%@page import="java.util.List"%>
<%@page import="co.edu.unal.pesi.vista.Servicio"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%

    Servicio servicio = (Servicio) session.getAttribute("servicio");
    if (servicio == null) {
        servicio = new Servicio();
        session.setAttribute("servicio", servicio);
    }

    List lstPCD = servicio.listarProcesoClasesDeDatos();
    List lstProc = servicio.listarProcesos();
    List lstCD = servicio.listarClasedatos();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>MATRIZ PROCESO - CLASE DE DATOS</title>
        <script type="text/css">

        </script>
        <style type="text/css">
            .tabla {
                font-family: Verdana, Geneva, sans-serif;
                font-size: 9px;
            }
        .linea {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	font-weight: bold;
}
        </style>
    </head>
    <body>
        <% if (lstPCD != null && lstProc != null) {%>
        <table width="46%" border="1" cellpadding="1" cellspacing="1" class="tabla">
            <caption>PROCESOS /CLASES DE DATOS</caption>
            <%
                int i = 0;
                for (java.util.Iterator it = lstProc.iterator(); it.hasNext();) {
                    Procesos p = (Procesos) it.next();

            %>
            <tr>
                <% if(i == 0){ %>
                <td>&nbsp;</td>
                <% }else{ %>
                <td class="linea"><%=p.getNombre()%></td>
                        
                <%} if (i == 0) {
                        for (java.util.Iterator it2 = lstCD.iterator(); it2.hasNext();) {
                            Clasedatos cd = (Clasedatos) it2.next();


                %>

                <td align="center" class="linea"><%=cd.getNombre()%></td>
                <%
                    }
                } else {
                    for (java.util.Iterator it2 = lstCD.iterator(); it2.hasNext();) {
                        Clasedatos cd = (Clasedatos) it2.next();
                %>
                <td align="center"><%=Utiles.buscarTipoUso(lstPCD, p, cd)%></td>
                <% }
                    }%>
            </tr>
            <% i++;
                }%>
        </table>
        <% }%>
    </body>
</html>