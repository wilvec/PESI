<%@page import="co.edu.unal.pesi.modelo.Clasedatos"%>
<%@page import="java.util.List"%>
<%@page import="co.edu.unal.pesi.vista.Servicio"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%

    Servicio servicio = (Servicio) session.getAttribute("servicio");
    if (servicio == null) {
        servicio = new Servicio();
        session.setAttribute("servicio", servicio);
    }

    List lstCd = servicio.listarClasedatos();

%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Documento sin título</title>
    </head>

    <body>
        <h1>CLASES DE DATOS</h1>
        <table width="75%" border="0" cellspacing="1" cellpadding="1">
            <tr>
                <td width="27%">NOMBRE</td>
                <td width="49%">DESCRIPCION</td>
            </tr>
            <% if(lstCd!= null){
        for(java.util.Iterator it = lstCd.iterator(); it.hasNext();){  
            Clasedatos cd = (Clasedatos)it.next();
      %>
        <tr>
          <td><%=cd.getNombre()%></td>
          <td><%=cd.getDescripcion()%></td>
        </tr>
      <% }  } %>
        </table>
        <p><a href="clasesdatos.jsp">AGREGAR</a></p>
        <p></p>
        <p><a href="index.jsp">VOLVER</a></p>
</body>
</html>