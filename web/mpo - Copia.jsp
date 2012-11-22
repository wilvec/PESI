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

    List lstG = servicio.listarProcesoOrganizaciones();
    List lstProc = servicio.listarProcesos();
    List lstO = servicio.listarOrganizaciones();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>MATRIZ PROCESO - ORGANIZACION</title>
        <script type="text/css">
            
        </script>
    </head>
    <body>
        <% if (lstG != null && lstProc != null) {%>
        <table width="46%" border="1" cellspacing="1" cellpadding="1">
            <caption>PROCESOS /ORGANIZACION</caption>
            <%
                int i = 0;
                for (java.util.Iterator it = lstProc.iterator(); it.hasNext();) {
                    Procesos p = (Procesos) it.next();
                    
            %>
            <tr>
                <td><%=p.getNombre()%></td>
                    
                    
                <% if (i == 0){
                        for (java.util.Iterator it2 = lstO.iterator(); it2.hasNext();) {
                            Organizaciones org = (Organizaciones) it2.next();

                %>
                <td align="center"><%=org.getNombre()%></td>
                <% } }else{ 
                        for (java.util.Iterator it2 = lstO.iterator(); it2.hasNext();) {
                            Organizaciones org = (Organizaciones) it2.next();
                %>
                <td align="center"><%=Utiles.buscarResposabilidad(lstG, p, org) %></td>
                <% } } %>
            </tr>
            <% i++; 
                }   %>
        </table>
        <% }%>
    </body>
</html>