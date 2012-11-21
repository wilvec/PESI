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

    ClasedatosJpaController servCD = servicio.getCtrlClaseDatos();

    Clasedatos clase = new Clasedatos();
    String descripcion = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
    String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");

    List lista = (List) session.getAttribute("listaEntidad");
    int index = Integer.valueOf(request.getParameter("entidad") == null ? "0" : request.getParameter("entidad"));
    Entidad ent = (Entidad) lista.get(index);
    clase.setNombre(nombre);
    clase.setDescripcion(descripcion);
    clase.setEntidadId(ent);
    clase.setCodigo(request.getParameter("codigo") == null ? "" : request.getParameter("codigo"));
    servCD.create(clase);
    out.print("TODO OK!");

%>