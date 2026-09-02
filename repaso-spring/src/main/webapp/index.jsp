<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Repaso Spring</title>
</head>
<body>
    <h1>Repaso Spring</h1>
    <p>Selecciona una opcion para navegar a los servlets de mediciones.</p>

    <form action="<%= request.getContextPath() %>/measurements" method="get">
        <button type="submit">Ver mediciones</button>
    </form>

    <form action="<%= request.getContextPath() %>/measurements/create" method="get">
        <button type="submit">Registrar medicion</button>
    </form>
</body>
</html>
