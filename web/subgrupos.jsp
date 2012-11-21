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
    }

    GruposJpaController servicioGrp = servicio.getCrlGrupos();
    List list = (List) session.getAttribute("listaGrupos");
    if (list == null) {
        list = servicioGrp.findGruposEntities();
        session.setAttribute("listaGrupos", list);
    }

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Subgrupos</title>
    </head>
    <body>
        <h1>SUBGRUPOS</h1>
        <form id="form1" name="form1" method="post" action="subgruporeg.jsp">
            <table width="45%" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td width="23%">NOMBRE</td>
                    <td width="77%"><input type="text" name="nombre" id="nombre" /></td>
                </tr>
                <tr>
                    <td>GRUPO</td>
                    <td>
                        <select name="grupo" id="grupo">
                            <%
                                int idx1 = 0;
                                for (java.util.Iterator it = list.iterator(); it.hasNext();) {
                                    Grupos grp = (Grupos) it.next();
                            %>
                            <option value="<%=idx1++%>"><%=grp.getNombre().toUpperCase()%></option>
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