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
            <td>GRUPO</td>
            <td colspan="2"><select name="grupo" id="depend8">
            </select></td>
          </tr>
          <tr>
            <td>SUBGRUPO</td>
            <td colspan="2"><select name="subgrupo" id="depend9">
            </select></td>
          </tr>
          <tr>
            <td>NOMBRE</td>
            <td colspan="2"><input name="textfield" type="text" id="textfield" size="80"></td>
          </tr>
          <tr>
            <td>Dependencia con mayor responsabilidad </td>
            <td width="40%"><select name="depend1" id="depend1">
            </select>
            <input type="button" name="button" id="button" value="Agregar"></td>
            <td width="37%">&nbsp;</td>
          </tr>
          <tr>
            <td> Dependencia con menor responsabilidad</td>
            <td><select name="depend2" id="depend2">
            </select>
            <input type="button" name="button2" id="button2" value="Agregar"></td>
            <td>&nbsp; </td>
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
            <td colspan="2"><textarea name="objetivos" cols="80" rows="5" id="objetivos"></textarea></td>
          </tr>
          <tr>
            <td rowspan="2">3. INSUMOS, INSUMOS</td>
            <td>&nbsp;</td>
            <td>4. DEPENDENCIA ORIGEN O PROVEEDORES</td>
          </tr>
          <tr>
            <td><select name="depend5" id="depend5">
            </select></td>
            <td><select name="depend4" id="depend4">
            </select>
            <input type="button" name="button4" id="button4" value="Agregar"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">&nbsp;</td>
          </tr>
          <tr>
            <td>5. ACTIVIDADES</td>
            <td colspan="2"><textarea name="actividades" cols="80" rows="5" id="actividades"></textarea></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">&nbsp;</td>
          </tr>
          <tr>
            <td rowspan="2">6. SALIDAS, PRODUCTOS</td>
            <td>&nbsp;</td>
            <td>7. DEPENDENCIA DESTINO O USUARIOS</td>
          </tr>
          <tr>
            <td><select name="depend6" id="depend6">
            </select></td>
            <td><select name="depend6" id="depend7">
            </select>
              <input type="button" name="button5" id="button5" value="Agregar"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">&nbsp;</td>
          </tr>
          <tr>
            <td>8. TIPO DE PROCESO</td>
            <td colspan="2"><select name="select" id="select">
              <option value="ENLINEA">EN LINEA</option>
              <option value="PORLOTE">POR LOTE</option>
              <option value="MANUAL">MANUAL</option>
            </select></td>
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
