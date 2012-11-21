<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            <td><select name="grupo" id="depend8">
            </select></td>
          </tr>
          <tr>
            <td>SUBGRUPO</td>
            <td><select name="subgrupo" id="depend9">
            </select></td>
          </tr>
          <tr>
            <td>NOMBRE</td>
            <td><input name="nombre" type="text" id="nombre" size="80"></td>
          </tr>
          <tr>
            <td>Dependencia con mayor responsabilidad </td>
            <td><select name="organizacion1" id="organizacion1">
            </select>
            <input type="button" name="btoadd" id="btoadd" value="Agregar">
            <input type="button" name="btoadd4" id="btoadd4" value="ver"></td>
          </tr>
          <tr>
            <td> Dependencia con menor responsabilidad</td>
            <td><select name="organizacion2" id="organizacion2">
            </select>
            <input type="button" name="btoadd2" id="btoadd2" value="Agregar">
            <input type="button" name="btoadd5" id="btoadd5" value="ver"></td>
          </tr>
          <tr>
            <td>Dependencia Relacionada</td>
            <td><select name="organizacion3" id="organizacion3">
            </select>
            <input type="button" name="btoadd3" id="btoadd3" value="Agregar">
            <input type="button" name="btoadd6" id="btoadd6" value="ver"></td>
          </tr>
          <tr>
            <td>2. OBJETIVOS</td>
            <td><textarea name="objetivos" cols="80" rows="5" id="objetivos"></textarea></td>
          </tr>
          <tr>
            <td rowspan="2">3. INSUMOS, INSUMOS</td>
            <td><select name="insumos" id="insumos">
            </select>
            <input type="button" name="btoAddInsumo" id="btoAddInsumo" value="Agregar"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
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
            <td>&nbsp;</td>
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
