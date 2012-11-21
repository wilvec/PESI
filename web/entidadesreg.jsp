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

    Entidad ent = new Entidad();
    String descripcion = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
    String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
    ent.setDescripcion(descripcion);
    ent.setNombre(nombre);
    EntidadJpaController servEntidad = servicio.getCtrlEntidad();
    ent.setTipoentidad(Integer.valueOf(request.getParameter("tipoentidad") == null ? "0" : request.getParameter("tipoentidad")));
    servEntidad.create(ent);
    try {
        out.print("TODO OK!");
    } catch (Exception e) {
        out.print("OCURRIO UN ERROR AL GUARDAR EL OBJETO: " + ent.getNombre());
        e.printStackTrace();
    }

%>