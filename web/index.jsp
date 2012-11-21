<%-- 
    Document   : index
    Created on : 20/11/2012, 06:49:28 PM
    Author     : wilvec
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>INFORMACION DE PROCESOS</h1>
        <table width="100%" border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="3">&nbsp;</td>
          </tr>
          <tr>
            <td width="23%">CODIGO</td>
            <td colspan="2"><input type="text" name="codigo" id="codigo"></td>
          </tr>
          <tr>
            <td height="26" colspan="3">1. IDENTIFICACION DE PROCESOS</td>
          </tr>
          <tr>
            <td>NOMBRE</td>
            <td colspan="2"><input name="textfield" type="text" id="textfield" size="80"></td>
          </tr>
          <tr>
            <td>Dependencia con mayor responsabilidad </td>
            <td width="36%"><select name="depend1" id="depend1">
            </select>
            <input type="button" name="button" id="button" value="Agregar"></td>
            <td width="41%">&nbsp;</td>
          </tr>
          <tr>
            <td> Dependencia con menor responsabilidad</td>
            <td><select name="depend2" id="depend2">
            </select>
            <input type="button" name="button2" id="button2" value="Agregar"></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>Dependencia Relacionada</td>
            <td><select name="depend3" id="depend3">
            </select>
            <input type="button" name="button3" id="button3" value="Agregar"></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>2. OBJETIVOS</td>
            <td colspan="2"><textarea name="textarea" cols="80" rows="5" id="textarea"></textarea></td>
          </tr>
          <tr>
            <td>3. INSUMOS, INSUMOS</td>
            <td>&nbsp;</td>
            <td>4. DEPENDENCIA </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">&nbsp;</td>
          </tr>
        </table>
        <p>&nbsp;</p>
    </body>
</html>
