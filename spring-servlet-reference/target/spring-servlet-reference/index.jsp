<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Spring Servlet Reference</title>
</head>
<body>
    <h1>Spring Servlet Reference</h1>
    <p>Selecciona una opcion para probar los servlets del proyecto.</p>

    <form action="<%= request.getContextPath() %>/records" method="get">
        <button type="submit">Ver records</button>
    </form>

    <form action="<%= request.getContextPath() %>/records/create" method="get">
        <button type="submit">Crear record</button>
    </form>

    <form action="<%= request.getContextPath() %>/records/find" method="get">
        <button type="submit">Buscar record</button>
    </form>

    <form action="<%= request.getContextPath() %>/records/update" method="get">
        <input type="hidden" name="id" value="1">
        <button type="submit">Actualizar record 1</button>
    </form>

    <form action="<%= request.getContextPath() %>/records/delete" method="get">
        <input type="hidden" name="id" value="1">
        <button type="submit">Eliminar record 1</button>
    </form>
</body>
</html>
