<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.edu.unal.pesi.VerificarAfinidad"%>
<%@page import="co.edu.unal.pesi.AfinidadParAPar"%>
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

    List lstG = servicio.listarProcesoClasesDeDatos();
    List lstProc = servicio.listarProcesos();
    List lstO = servicio.listarClasedatos();

    List listaOriginal = Utiles.pasarAArray(lstG, lstProc, lstO);

    AfinidadParAPar af = new AfinidadParAPar();
    af.setMatrizValoresLlenos((ArrayList) listaOriginal);
    af.cambiar();
    List lstAFParOriginal = af.getMatrizValoresLlenos();
    VerificarAfinidad vf = new VerificarAfinidad();
    vf.setMatriz((ArrayList) listaOriginal);
    vf.verificar();
    List lisOrdenado = vf.getMatriz();
    af.setMatrizValoresLlenos(vf.getMatriz());
    af.cambiar();
    List lstCambio = af.getMatrizValoresLlenos();

    





%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>MATRIZ PROCESO - ORGANIZACION</title>
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
        <table width="46%" border="1" cellpadding="1" cellspacing="1" class="tabla">
            <%
                List l2 = (List) listaOriginal.get(0);
                for (int i = 0; i < l2.size(); i++) {
            %>    
            <tr>
                <%
                    for (int j = 0; j < listaOriginal.size(); j++) {
                        List l3 = (List) listaOriginal.get(j);

                %>
                <td align="center"><%=l3.get(i)%></td>
                <% }%>
            </tr>
            <%   }%>
        </table>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <table width="46%" border="1" cellpadding="1" cellspacing="1" class="tabla">
            <%
                l2 = (List) lstAFParOriginal.get(0);
                for (int i = 0; i < l2.size(); i++) {
            %>    
            <tr>
                <%
                    for (int j = 0; j < lstAFParOriginal.size(); j++) {
                        List l3 = (List) lstAFParOriginal.get(j);

                %>
                <td align="center"><%=l3.get(i)%></td>
                <% }%>
            </tr>
            <%   }%>
        </table>
<p>&nbsp;</p>
        <p>&nbsp;</p>
        <table width="46%" border="1" cellpadding="1" cellspacing="1" class="tabla">
            <%
                l2 = (List) lisOrdenado.get(0);
                for (int i = 0; i < l2.size(); i++) {
            %>    
            <tr>
                <%
                    for (int j = 0; j < lisOrdenado.size(); j++) {
                        List l3 = (List) lisOrdenado.get(j);

                %>
                <td align="center"><%=l3.get(i)%></td>
                <% }%>
            </tr>
            <%   }%>
        </table>            
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <table width="46%" border="1" cellpadding="1" cellspacing="1" class="tabla">
            <%
                l2 = (List) lstCambio.get(0);
                for (int i = 0; i < l2.size(); i++) {
            %>    
            <tr>
                <%
                    for (int j = 0; j < lstCambio.size(); j++) {
                        List l3 = (List) lstCambio.get(j);
                %>
                <td align="center"><%=l3.get(i)%></td>
                <% }%>
            </tr>
            <%   }%>
        </table>

    </body>
</html>