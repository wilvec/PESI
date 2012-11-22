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
        <form name="form1" method="post" action="proceso2reg.jsp">
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
                <td>2. OBJETIVOS</td>
                <td><textarea name="objetivos" cols="80" rows="5" id="objetivos"></textarea></td>
            </tr>
            <tr>
                <td>4. ACTIVIDADES</td>
                <td><textarea name="actividades" cols="80" rows="5" id="actividades"></textarea></td>
            </tr>
            <tr>
              <td>TIPO DE PROCESO</td>
              <td><select name="tipoproceso" id="tipoproceso">
                <option value="1">EN LINEA</option>
                <option value="2">POR LOTE</option>
                <option value="3">MANUAL</option>
              </select></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="button" id="button" value="Guardar"></td>
            </tr>
        </table>
        </form>
        <p>&nbsp;</p>
        <p><a href="lstProceso.jsp">VOLVER</a></p>
    </body>
</html>
