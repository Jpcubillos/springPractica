<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Intro Spring Profe</title>
</head>
<body>
    <h1>Intro Spring Profe</h1>
    <p>Selecciona una opcion para entrar al servlet disponible.</p>

    <form action="<%= request.getContextPath() %>/estudiante" method="get">
        <button type="submit">Gestionar estudiantes</button>
    </form>
</body>
</html>
