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
    List lstOrganizaciones = servicio.listarOrganizaciones();
    Procesos proc = (Procesos) session.getAttribute("proceso");
    List lstProOrg = servicio.listarProcesoOrganizaciones();
    List lstCD = servicio.listarClasedatos();
    List lstPCD = servicio.listarPCD();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INFORMACION DE PROCESOS</title>
        <script type="text/javascript">
            var sel1 = null;
            function agregarOrganizacion() {
                var url = "llenatablaprocesos.jsp?";
                var depeCombo = document.getElementById('dependencia');
                var respCombo = document.getElementById('responsabilidad');
                var idDep = depeCombo.options[depeCombo.selectedIndex].value;
                var resp = respCombo.options[respCombo.selectedIndex].value;
                url += "depe=" + idDep + "&resp=" + resp;
                oAjax = getAjax();
                oAjax.onreadystatechange = function() {
                    if (oAjax.readyState == 4) {
                        var resultado = oAjax.responseText;
                        document.getElementById('divDependencias').innerHTML = resultado;
                    }
                };
                oAjax.open("GET", url, true);
                oAjax.send(null);
            }

            function agregarInsumos() {
                var url = "llenarinsumos.jsp?";
                var depeCombo = document.getElementById('insumos');
                var idIns = depeCombo.options[depeCombo.selectedIndex].value;

                url += "insumo=" + idIns;
                oAjax = getAjax();
                oAjax.onreadystatechange = function() {
                    if (oAjax.readyState == 4) {
                        var resultado = oAjax.responseText;
                        document.getElementById('divInsumos').innerHTML = resultado;
                    }
                };
                oAjax.open("GET", url, true);
                oAjax.send(null);

            }
            function agregarSalida() {
                var url = "llenarsalida.jsp?";
                var depeCombo = document.getElementById('salida');
                var idIns = depeCombo.options[depeCombo.selectedIndex].value;
                var chk = document.getElementById('creausa').value;
                url += "salida=" + idIns + "&creausa=" + chk;
                oAjax = getAjax();
                oAjax.onreadystatechange = function() {
                    if (oAjax.readyState == 4) {
                        var resultado = oAjax.responseText;
                        document.getElementById('divSalidas').innerHTML = resultado;
                    }
                };
                oAjax.open("GET", url, true);
                oAjax.send(null);
            }

            function getAjax() {
                if (window.XMLHttpRequest) {
                    return new XMLHttpRequest();
                } else {
                    return new ActiveXObject("Microsoft.XMLHTTP");
                }
            }

        </script>
    </head>
    <body>
        <h1>INFORMACION DE PROCESOS</h1>
        <table width="76%" border="0" cellspacing="1" cellpadding="1">
            <tr>
                <td colspan="2">&nbsp;</td>
            </tr>
            <tr>
                <td width="34%">CODIGO</td>
                <td><%=proc.getCodigo()%>  </td>
            </tr>
            <tr>
                <td height="26" colspan="2">1. IDENTIFICACION DE PROCESOS</td>
            </tr>
            <tr>
                <td>NOMBRE</td>
                <td><%=proc.getNombre()%></td>
            </tr>
            <tr>
                <td>Dependencias con responsabilidad en el proceso</td>
                <td>
                    <select name="dependencia" id="dependencia">
                        <%
                            for (java.util.Iterator it = lstOrganizaciones.iterator(); it.hasNext();) {
                                Organizaciones org = (Organizaciones) it.next();
                        %>
                        <option value="<%=org.getId()%>"><%=org.getNombre()%></option>
                        <%
                            }
                        %>
                    </select>
                    <select name="responsabilidad" id="responsabilidad">
                        <option value="1">RESPONSABILIDAD MAYOR. TOMADOR DE DESICION</option>
                        <option value="2">PARTICIPACION MAYOR</option>
                        <option value="3">ALGUNA PARTICIPACION</option>
                    </select>
                    <input type="button" name="agregar" id="agregar" value="Agregar" onclick="agregarOrganizacion()"></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>
                    <div id="divDependencias">
                        <table width="100%" border="0" cellspacing="1" cellpadding="1">
                            <tr>
                                <td align="center"><strong>DEPENDENCIA</strong></td>
                                <td align="center"><strong>RESPONSABILIDAD</strong></td>
                            </tr>
                            <% for (java.util.Iterator it = lstProOrg.iterator(); it.hasNext();) {
                                    Procesosorganizaciones pog = (Procesosorganizaciones) it.next();
                            %>
                            <tr>
                                <td><%=pog.getOrganizaciones().getNombre()%></td>
                                <td><%=pog.getResponsabilidad() == 1 ? "RESPONSABILIDAD MAYOR. TOMADOR DE DESICION" : pog.getResponsabilidad() == 2 ? "PARTICIPACION MAYOR" : "ALGUNA PARTICIPACION"%></td>
                            </tr>
                            <% }%>                        
                        </table>
                    </div>

                </td>
            </tr>
            <tr>
                <td rowspan="2">3. ENTRADAS, INSUMOS</td>
                <td>
                    <select name="insumos" id="insumos">
                        <% for (java.util.Iterator it = lstCD.iterator(); it.hasNext();) {
                                Clasedatos clsd = (Clasedatos) it.next();
                        %>
                        <option value="<%=clsd.getId()%>"><%=clsd.getNombre()%></option>
                        <% }%>
                    </select>
                    <input type="button" name="btoAddInsumo" id="btoAddInsumo" value="Agregar" onClick="agregarInsumos()"></td>
            </tr>
            <tr>
                <td><div id="divInsumos">
                        <table width="100%" border="0" cellspacing="1" cellpadding="1">
                            <tr>
                                <td align="center"><strong>ENTRADAS, INSUMOS</strong></td>
                            </tr>
                            <% for (java.util.Iterator it = lstPCD.iterator(); it.hasNext();) {
                                    Procesosclasesdatos pcd = (Procesosclasesdatos) it.next();
                            %>
                            <tr>
                                <td><%=pcd.getClasedatos().getNombre()%></td>
                            </tr>
                            <% }%>
                        </table>
                    </div></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td rowspan="2">5. SALIDAS, PRODUCTOS</td>
                <td>
                    <select name="salidas" id="salidas">
                        <% for (java.util.Iterator it = lstCD.iterator(); it.hasNext();) {
                                Clasedatos clsd = (Clasedatos) it.next();
                        %>
                        <option value="<%=clsd.getId()%>"><%=clsd.getNombre()%></option>
                        <% }%>

                    </select>
                    <input type="checkbox" name="creausa" id="creausa">
                    (Crea y Usa)  
                    <input type="button" name="btoAddSalida" id="btoAddSalida" value="Agregar" onclick="agregarSalida()"></td>
            </tr>
            <tr>
                <td><div id="divSalidas">
                        <table width="100%" border="0" cellspacing="1" cellpadding="1">
                            <tr>
                                <td align="center"><strong>SALIDAS, PRODUCTOS</strong></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </div></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
        </table>
        <p>&nbsp;</p>
    </body>
</html>
