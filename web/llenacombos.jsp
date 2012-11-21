<%@page import="java.util.List" %>
<%@page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" %>
<%@page import="co.edu.unal.pesi.modelo.*" %>
<%@page import="co.edu.unal.pesi.servicio.*" %>
<%@page import="co.edu.unal.pesi.vista.*" %>
<%
    session.removeAttribute("listaGrupos");
    Servicio servicio = (Servicio) session.getAttribute("servicio");
    if (servicio == null) {
        servicio = new Servicio();
        session.setAttribute("servicio", servicio);
    }

    List lstSubgrupos = servicio.listarSubgrupos();
    if (lstSubgrupos != null) {
%>
<%
    int idx1 = 0;
    int idGrupo = Integer.valueOf(request.getParameter("grupo") == null ? "0" : request.getParameter("grupo"));
    for (java.util.Iterator it = lstSubgrupos.iterator(); it.hasNext();) {
        Subgrupos sub1 = (Subgrupos) it.next();
        if (sub1.getGruposId().getId().intValue() == idGrupo) {
%>
<option value="<%=idx1++%>"><%=sub1.getNombre().toUpperCase()%></option>
<%
            }
        }
    }
%>