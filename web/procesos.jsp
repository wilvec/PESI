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
    List lstGrupos = servicio.listarGrupos();

    List lstOrganizaciones = servicio.listarOrganizaciones();
    List lstProcesoOrganizacion = (List) session.getAttribute("lstProcesoOrganizacion");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INFORMACION DE PROCESOS</title>
        <script type="text/javascript">
            var sel1 = null;
            function cambiaSubgrupo(obj) {
                var selgrupo = obj.options[obj.selectedIndex].value;
                var url = "llenacombos.jsp?";
                sel1 = obj;
                url += "subgrupo=yes&grupo=" + selgrupo;
                oAjax = getAjax();
                oAjax.onreadystatechange = function() {
                    if (oAjax.readyState == 4) {
                        var objSg = document.getElementById('subgrupo');
                        var resultado = oAjax.responseText;
                        resultado = "x" + resultado;
                        parent1 = objSg.parentElement;
                        objSg.innerHTML = "";
                        objSg.innerHTML = resultado;
                        parent1.innerHTML = objSg.outerHTML;
                    }
                }
                oAjax.open("GET", url, true);
                oAjax.send(null);
            }

            function agregarOrganizacion() {
                var url = "llenatablaprocesos.jsp?";
                var depeCombo = document.getElementById('dependencia');
                var respCombo = document.getElementById('responsabilidad');
                var idDep = depeCombo.options[depeCombo.selectedIndex].value;
                var resp = respCombo.options[respCombo.selectedIndex].value;
                url+="depe="+idDep+"&resp="+resp;
                oAjax = getAjax();
                oAjax.onreadystatechange = function() {
                    if (oAjax.readyState == 4) {
                        var resultado = oAjax.responseText;
                    }
                }
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
                <td><input type="text" name="codigo" id="codigo"></td>
            </tr>
            <tr>
                <td height="26" colspan="2">1. IDENTIFICACION DE PROCESOS</td>
            </tr>
            <tr>
                <td>GRUPO</td>
                <td>
                    <select name="grupo" id="grupo" onchange="cambiaSubgrupo(this)">
                        <option value="" selected></option>
                        <%
                            for (java.util.Iterator it = lstGrupos.iterator(); it.hasNext();) {
                                Grupos grp = (Grupos) it.next();
                        %>
                        <option value="<%=grp.getId()%>"><%=grp.getNombre().toUpperCase()%></option>
                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>SUBGRUPO</td>
                <td>
                    <select name="subgrupo" id="subgrupo">
                    </select>

                </td>
            </tr>
            <tr>
                <td>NOMBRE</td>
                <td><input name="nombre" type="text" id="nombre" size="80"></td>
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
                    <select name="responsabilida" id="responsabilida">
                        <option value="1">RESPONSABILIDAD MAYOR. TOMADOR DE DESICION</option>
                        <option value="2">PARTICIPACION MAYOR</option>
                        <option value="3">ALGUNA PARTICIPACION</option>
                    </select>
                    <input type="button" name="agregar" id="agregar" value="Agregar" onclick="agregarOrganizacion()"></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><div id="divDependencias">
                        <table width="100%" border="0" cellspacing="1" cellpadding="1">
                            <tr>
                                <td align="center"><strong>DEPENDENCIA</strong></td>
                                <td align="center"><strong>RESPONSABILIDAD</strong></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </div></td>
            </tr>
            <tr>
                <td>2. OBJETIVOS</td>
                <td><textarea name="objetivos" cols="80" rows="5" id="objetivos"></textarea></td>
            </tr>
            <tr>
                <td rowspan="2">3. ENTRADAS, INSUMOS</td>
                <td><select name="insumos" id="insumos">
                    </select>
                    <input type="button" name="btoAddInsumo" id="btoAddInsumo" value="Agregar"></td>
            </tr>
            <tr>
                <td><div id="divInsumos">
                        <table width="100%" border="0" cellspacing="1" cellpadding="1">
                            <tr>
                                <td align="center"><strong>ENTRADAS, INSUMOS</strong></td>
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
                <td>4. ACTIVIDADES</td>
                <td><textarea name="actividades" cols="80" rows="5" id="actividades"></textarea></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td rowspan="2">5. SALIDAS, PRODUCTOS</td>
                <td><select name="salidas" id="salidas">
                    </select>
                    <input type="button" name="btoAddSalida" id="btoAddSalida" value="Agregar"></td>
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
                <td>6. TIPO DE PROCESO</td>
                <td><select name="tipoproceso" id="tipoproceso">
                        <option value="ENLINEA">EN LINEA</option>
                        <option value="PORLOTE">POR LOTE</option>
                        <option value="MANUAL">MANUAL</option>
                    </select></td>
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
                <td>&nbsp;</td>
                <td><input type="button" name="button" id="button" value="Guardar"></td>
            </tr>
        </table>
        <p>&nbsp;</p>
    </body>
</html>
