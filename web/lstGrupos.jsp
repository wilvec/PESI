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

    List lstG = servicio.listarGrupos();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Documento sin título</title>
</head>

<body>
<h1>GRUPOS </h1>
<table width="62%" border="0" cellspacing="1" cellpadding="1">
  <tr>
    <td>NOMBRE</td>
    <td>DESCRIPCION</td>
  </tr>
  <% if(lstG!=null){ 
    for(java.util.Iterator it = lstG.iterator(); it.hasNext();)  {
        Grupos grp = (Grupos)it.next();
    
  %> 
  <tr>
    <td><%=grp.getNombre()%></td>
    <td><%=grp.getDescripcion()%></td>
  </tr>
    <% }} %>  
</table>
<p><a href="grupos.jsp">AGREGAR</a><br />
</p>
<p><a href="index.jsp">VOLVER</a></p>
<p>&nbsp; </p>
</body>
</html>