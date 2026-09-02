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

@WebServlet("/records/create")
public class CreateRecordServlet extends HttpServlet {
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
        PrintWriter out = ServletHtml.start(response, "Crear Record");
        out.println("<form method='post' action='" + contextPath + "/records/create'>");
        out.println("<p>Id: <input name='id'></p>");
        out.println("<p>Parent id: <input name='parentId'></p>");
        out.println("<p>Timestamp: <input name='timestamp'></p>");
        out.println("<p>Valor: <input name='value'></p>");
        out.println("<p>Descripcion: <input name='description'></p>");
        out.println("<p>Activo: <input type='checkbox' name='active' value='true' checked></p>");
        out.println("<button type='submit'>Guardar</button>");
        out.println("</form>");
        out.println("<p><a href='" + contextPath + "/records'>Regresar</a></p>");
        ServletHtml.end(out);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Long parentId = Long.parseLong(request.getParameter("parentId"));
            long timestamp = Long.parseLong(request.getParameter("timestamp"));
            double value = Double.parseDouble(request.getParameter("value"));
            String description = request.getParameter("description");
            boolean active = Boolean.parseBoolean(request.getParameter("active"));

            recordService.save(new Record(id, parentId, timestamp, value, description, active));
            response.sendRedirect(contextPath + "/records");
        } catch (NumberFormatException ex) {
            ServletHtml.error(response, "Los campos numericos deben tener formato valido", contextPath + "/records/create");
        } catch (IllegalArgumentException ex) {
            ServletHtml.error(response, ex.getMessage(), contextPath + "/records/create");
        }
    }
}
