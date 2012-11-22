<%@page import="com.sun.org.apache.bcel.internal.generic.AALOAD"%>
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
    int idIns = Integer.valueOf(request.getParameter("salida") == null ? "0" : request.getParameter("salida"));
    List lstCD = servicio.listarClasedatos();
    Procesos proc = (Procesos) session.getAttribute("proceso");
    Clasedatos cd = Utiles.buscarClasedatosId(lstCD, idIns);
    ProcesosclasesdatosJpaController pcdServ = servicio.getCtrlPCD();
    Procesosclasesdatos pcd = new Procesosclasesdatos();
    String stCU = request.getParameter("creausa") == null ? "" : request.getParameter("creausa");
    String creaUsa = stCU.toUpperCase().equals("TRUE") ? "CU" : "C";
    List lstPCD = servicio.listarPCD();
    int encontrado = 0;

    try {
        pcd.setClasedatos(cd);
        pcd.setProcesos(proc);
        pcd.setTipouso(creaUsa);
        ProcesosclasesdatosPK pcdPK = new ProcesosclasesdatosPK();
        pcdPK.setClasedatosId(cd.getId());
        pcdPK.setProcesosId(proc.getId());
        pcd.setProcesosclasesdatosPK(pcdPK);
        for (java.util.Iterator it = lstPCD.iterator(); it.hasNext();) {
            Procesosclasesdatos pcd1 = (Procesosclasesdatos) it.next();
            if (pcd1.getClasedatos().getId().equals(cd.getId()) && (pcd1.getTipouso().equals("C") || pcd1.getTipouso().equals("CU"))) {
                encontrado++;
            }
        }
        if (encontrado <= 0) {
            pcdServ.create(pcd);
        }
    } catch (Exception e) {
    }
    lstPCD = servicio.listarPCD();

%>
<table width="100%" border="0" cellspacing="1" cellpadding="1">
    <tr>
        <td align="center"><strong>SALIDAS, PRODUCTOS</strong></td>
    </tr>
    <% for (java.util.Iterator it = lstPCD.iterator(); it.hasNext();) {
            pcd = (Procesosclasesdatos) it.next();

            if (pcd.getProcesos().equals(proc) && (pcd.getTipouso().equals("C") || pcd.getTipouso().equals("CU"))) {
    %>
    <tr>
        <td><%=pcd.getClasedatos().getNombre()%></td>
    </tr>
    <% }
        }%>
</table>