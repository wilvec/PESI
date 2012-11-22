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
    Procesosorganizaciones procOrg = new Procesosorganizaciones();
    int idOrg = Integer.valueOf(request.getParameter("depe") == null ? "0" : request.getParameter("depe"));
    int resp = Integer.valueOf(request.getParameter("resp") == null ? "0" : request.getParameter("resp"));
    List lstOrga = servicio.listarOrganizaciones();
    Procesos proc = (Procesos) session.getAttribute("proceso");
    Organizaciones org = Utiles.buscarOrganizacionesId(lstOrga, idOrg);
    ProcesosorganizacionesJpaController servPO = servicio.getCtrlProcOrga();
    try {
        procOrg.setOrganizaciones(org);
        procOrg.setProcesos(proc);
        procOrg.setResponsabilidad(resp);
        ProcesosorganizacionesPK pogPK = new ProcesosorganizacionesPK();
        pogPK.setOrganizacionesId(org.getId());
        pogPK.setProcesosId(proc.getId());
        procOrg.setProcesosorganizacionesPK(pogPK);
        servPO.create(procOrg);
    } catch (Exception e) {
        e.printStackTrace();
    }

    List lstProOrg = servicio.listarProcesoOrganizaciones();
%>
<table width="100%" border="0" cellspacing="1" cellpadding="1">
    <tr>
        <td align="center"><strong>DEPENDENCIA</strong></td>
        <td align="center"><strong>RESPONSABILIDAD</strong></td>
    </tr>
    <% for (java.util.Iterator it = lstProOrg.iterator(); it.hasNext();) {
            Procesosorganizaciones pog = (Procesosorganizaciones) it.next();
            if (pog.getProcesos().equals(proc)) {
    %>
    <tr>
        <td><%=pog.getOrganizaciones().getNombre()%></td>
        <td><%=pog.getResponsabilidad() == 1 ? "RESPONSABILIDAD MAYOR. TOMADOR DE DESICION" : pog.getResponsabilidad() == 2 ? "PARTICIPACION MAYOR" : "ALGUNA PARTICIPACION"%></td>
    </tr>
    <% }
                                }%>
</table>