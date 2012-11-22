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

    List lstSubgrupos = (List) session.getAttribute("listaSubgrupos");
    if (lstSubgrupos == null) {
        lstSubgrupos = servicio.listarSubgrupos();
        session.setAttribute("listaSubgrupos", lstSubgrupos);
    }

    Procesos proc = new Procesos();
    String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
    String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
    String objetivos = request.getParameter("objetivos") == null ? "" : request.getParameter("objetivos");
    String actividades = request.getParameter("actividades") == null ? "" : request.getParameter("actividades");
    int idSubgrupo = Integer.valueOf(request.getParameter("subgrupo") == null ? "0" : request.getParameter("subgrupo"));
    int tipoProceso = Integer.valueOf(request.getParameter("tipoproceso") == null ? "0" : request.getParameter("tipoproceso"));
    Subgrupos sub2 = Utiles.buscarSubGrupoId(lstSubgrupos, idSubgrupo);
    proc.setCodigo(codigo);
    proc.setNombre(nombre);
    proc.setObjetivos(objetivos);
    proc.setActividades(actividades);
    proc.setSubgruposId(sub2);
    proc.setTipo(tipoProceso);
    ProcesosJpaController servProc = servicio.getCtrlProcesos();
    servProc.create(proc);
    session.setAttribute("proceso", proc);
    response.sendRedirect("procesos3.jsp");
%>