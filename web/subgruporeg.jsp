<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="co.edu.unal.pesi.modelo.*"%>
<%@page import="co.edu.unal.pesi.servicio.*"%>
<%@page import="co.edu.unal.pesi.vista.*"%>
<%
    Servicio servicio = (Servicio) session.getAttribute("servicio");
    if (servicio == null) {
        servicio = new Servicio();
        session.setAttribute("servicio", servicio);
    }
    Subgrupos sub1 = new Subgrupos();
    String descripcion = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
    String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
    sub1.setNombre(nombre);
    sub1.setDescripcion(descripcion);
    SubgruposJpaController servicioGrp = servicio.getCtrlSubGrupos();
    List lista = (List) session.getAttribute("listaGrupos");
    int index = Integer.valueOf(request.getParameter("grupo") == null ? "0" : request.getParameter("grupo"));
    Grupos grup = (Grupos) lista.get(index);
    sub1.setGruposId(grup);
    servicioGrp.create(sub1);
    try {
        out.print("TODO OK!");
    } catch (Exception e) {
        out.print("OCURRIO UN ERROR AL GUARDAR EL OBJETO: " + sub1.getNombre());
        e.printStackTrace();
    }

%>