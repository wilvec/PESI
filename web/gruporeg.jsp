<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="co.edu.unal.pesi.modelo.*"%>
<%@page import="co.edu.unal.pesi.servicio.*"%>
<%@page import="co.edu.unal.pesi.vista.*"%>
<%
    Servicio servicio = (Servicio) session.getAttribute("servicio");
    if (servicio == null) {
        servicio = new Servicio();

    }

    Grupos grupo = new Grupos();
    String descripcion = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
    String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
    grupo.setDescripcion(descripcion);
    grupo.setNombre(nombre);
    GruposJpaController ServicioGrp = servicio.getCrlGrupos();
    ServicioGrp.create(grupo);
    try {
        out.print("TODO OK!");
    } catch (Exception e) {
        out.print("OCURRIO UN ERROR AL GUARDAR EL OBJETO: " + grupo.getNombre());
        e.printStackTrace();
    }

%>