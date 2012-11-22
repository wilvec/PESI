
<%@page import="co.edu.unal.pesi.modelo.*"%>
<%@page import="java.util.List"%>
<%@page import="co.edu.unal.pesi.vista.Servicio"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%
    session.removeAttribute("proceso");
    Servicio servicio = (Servicio) session.getAttribute("servicio");
    if (servicio == null) {
        servicio = new Servicio();
        session.setAttribute("servicio", servicio);
    }

    List lstProc = servicio.listarProcesos();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>LISTADO PROCESOS</title>
    </head>
    <body>
        <h1>PROCESOS</h1>
        <table width="75%" border="0" cellspacing="1" cellpadding="1">
            <tr>
                <td width="27%">NOMBRE</td>
                <td width="49%">OBJETIVOS</td>
            </tr>
            <% if (lstProc != null) {
                    for (java.util.Iterator it = lstProc.iterator(); it.hasNext();) {
                        Procesos proc = (Procesos) it.next();
            %>
            <tr>
                <td><a href="procesos3.jsp?procid=<%=proc.getId()%>"><%=proc.getNombre()%></a></td>
                <td><%=proc.getObjetivos()%></td>
            </tr>
            <% }
    }%>      
        </table>
        <p><a href="proceso2.jsp">AGREGAR</a></p>
        <p><a href="index.jsp">VOLVER</a></p>
    </body>
</html>