<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ORGANIZACIONES</title>
</head>
<body>
<h1>ORGANIZACION</h1>
<form name="form1" method="post" action="organizacionreg.jsp">
<table width="64%" border="0" cellspacing="1" cellpadding="1">
  <tr>
    <td width="26%">&nbsp;</td>
    <td width="74%">&nbsp;</td>
  </tr>
  <tr>
    <td>NOMBRE</td>
    <td><input name="nombre" type="text" id="nombre" size="70" /></td>
  </tr>
  <tr>
    <td>DESCRIPCION</td>
    <td><textarea name="descripcion" id="descripcion" cols="70" rows="3"></textarea></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center"><input type="submit" name="guardar" id="guardar" value="Guardar" /></td>
  </tr>
</table>
<p><a href="lstorganizacion.jsp">VOLVER</a></p>
</form>
</body>
</html>