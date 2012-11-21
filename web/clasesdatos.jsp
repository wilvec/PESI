<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import="co.edu.unal.pesi.modelo.*"%>
<%@page import="co.edu.unal.pesi.servicio.*"%>
<%@page import="co.edu.unal.pesi.vista.*"%>
<%

    session.removeAttribute("listaGrupos");
    Servicio servicio = (Servicio) session.getAttribute("servicio");
    if (servicio == null) {
        servicio = new Servicio();
        session.setAttribute("servicio", servicio);
    }


    EntidadJpaController servEntidad = servicio.getCtrlEntidad();


    List list = (List) session.getAttribute("listaEntidad");
    if (list == null) {
        list = servEntidad.findEntidadEntities();
        session.setAttribute("listaEntidad", list);
    }

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Subgrupos</title>
    </head>
    <body>
        <h1>CLASES DE DATOS</h1>
        <form id="form1" name="form1" method="post" action="clasedatosreg.jsp">
            <table width="45%" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>CODIGO</td>
                    <td><input name="codigo" type="text" id="codigo" size="15" /></td>
                </tr>
                <tr>
                    <td width="23%">NOMBRE</td>
                    <td width="77%"><input name="nombre" type="text" id="nombre" size="60" /></td>
                </tr>
                <tr>
                    <td>ENTIDAD</td>
                    <td>
                        <select name="entidad" id="entidad">
                            <%
                                int idx1 = 0;
                                for (java.util.Iterator it = list.iterator(); it.hasNext();) {
                                    Entidad ent = (Entidad) it.next();
                            %>
                            <option value="<%=idx1++%>"><%=ent.getNombre().toUpperCase()%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>DESCRIPCION</td>
                    <td><textarea name="descripcion" id="descripcion" cols="45" rows="5"></textarea></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="center"><input type="submit" name="button" id="button" value="Guardar" /></td>
                </tr>
            </table>
        </form>
        <p>&nbsp;</p>
    </body>
</html>