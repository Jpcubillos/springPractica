package com.reference.servlet;

import com.reference.model.Record;
import com.reference.service.RecordService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/records")
public class ListRecordsServlet extends HttpServlet {
    private RecordService recordService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        recordService = context.getBean(RecordService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        try {
            List<Record> records = recordService.findAll();
            String limitParameter = request.getParameter("limit");
            int limit = limitParameter == null || limitParameter.isBlank()
                    ? records.size()
                    : Integer.parseInt(limitParameter);

            PrintWriter out = ServletHtml.start(response, "Records");
            out.println("<p><a href='" + contextPath + "/records/create'>Crear</a> | "
                    + "<a href='" + contextPath + "/records/find'>Buscar</a></p>");
            out.println("<form method='get' action='" + contextPath + "/records'>");
            out.println("<p>Limite: <input name='limit'> <button type='submit'>Aplicar</button></p>");
            out.println("</form>");
            out.println("<table border='1' cellpadding='5'>");
            out.println("<tr><th>Id</th><th>Parent</th><th>Timestamp</th><th>Valor</th><th>Descripcion</th><th>Activo</th><th>Acciones</th></tr>");
            for (int i = 0; i < records.size() && i < limit; i++) {
                Record record = records.get(i);
                out.println("<tr>");
                out.println("<td>" + record.getId() + "</td>");
                out.println("<td>" + record.getParentId() + "</td>");
                out.println("<td>" + record.getTimestamp() + "</td>");
                out.println("<td>" + record.getValue() + "</td>");
                out.println("<td>" + ServletHtml.escape(record.getDescription()) + "</td>");
                out.println("<td>" + record.isActive() + "</td>");
                out.println("<td><a href='" + contextPath + "/records/update?id=" + record.getId() + "'>Editar</a> "
                        + "<a href='" + contextPath + "/records/delete?id=" + record.getId() + "'>Eliminar</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            ServletHtml.end(out);
        } catch (NumberFormatException ex) {
            ServletHtml.error(response, "El limite debe ser un numero entero", contextPath + "/records");
        }
    }
}
