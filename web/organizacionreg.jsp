<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="co.edu.unal.pesi.modelo.*,co.edu.unal.pesi.servicio.*, co.edu.unal.pesi.vista.Servicio"%>
<%
    Servicio servicio = (Servicio) session.getAttribute("servicio");
    if (servicio == null) {
        servicio = new Servicio();
        session.setAttribute("servicio", servicio);
    }

    OrganizacionesJpaController ServicioOrg = servicio.getControlOrganizaciones();
    Organizaciones obj = new Organizaciones();
    String descripcion = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
    String nombre = request.getParameter("nombre");
    obj.setDescripcion(descripcion);
    obj.setNombre(nombre);
    try {
        ServicioOrg.create(obj);
        out.print("TODO OK!");
    } catch (Exception e) {
        out.print("OCURRIO UN ERROR AL GUARDAR EL OBJETO: " + obj.getNombre());
        e.printStackTrace();
    }

%>