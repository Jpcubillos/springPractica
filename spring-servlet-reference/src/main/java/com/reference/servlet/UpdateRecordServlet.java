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

@WebServlet("/records/update")
public class UpdateRecordServlet extends HttpServlet {
    private RecordService recordService;

    // Obtiene desde Spring el RecordService que usara el servlet.
    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        recordService = context.getBean(RecordService.class);
    }

    // Busca el Record actual y muestra el formulario con sus datos cargados.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Record record = recordService.findRequiredById(id);
            PrintWriter out = ServletHtml.start(response, "Actualizar Record");
            out.println("<form method='post' action='" + contextPath + "/records/update'>");
            out.println("<input type='hidden' name='id' value='" + record.getId() + "'>");
            out.println("<p>Parent id: <input name='parentId' value='" + record.getParentId() + "'></p>");
            out.println("<p>Timestamp: <input name='timestamp' value='" + record.getTimestamp() + "'></p>");
            out.println("<p>Valor: <input name='value' value='" + record.getValue() + "'></p>");
            out.println("<p>Descripcion: <input name='description' value='" + ServletHtml.escape(record.getDescription()) + "'></p>");
            out.println("<p>Activo: <input type='checkbox' name='active' value='true' "
                    + (record.isActive() ? "checked" : "") + "></p>");
            out.println("<button type='submit'>Actualizar</button>");
            out.println("</form>");
            out.println("<p><a href='" + contextPath + "/records'>Regresar</a></p>");
            ServletHtml.end(out);
        } catch (NumberFormatException ex) {
            ServletHtml.error(response, "El id debe ser numerico", contextPath + "/records");
        } catch (IllegalArgumentException ex) {
            ServletHtml.error(response, ex.getMessage(), contextPath + "/records");
        }
    }

    // Lee los parametros editados, arma el Record y lo actualiza por service.
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

            recordService.update(new Record(id, parentId, timestamp, value, description, active));
            response.sendRedirect(contextPath + "/records");
        } catch (NumberFormatException ex) {
            ServletHtml.error(response, "Los campos numericos deben tener formato valido", contextPath + "/records");
        } catch (IllegalArgumentException ex) {
            ServletHtml.error(response, ex.getMessage(), contextPath + "/records");
        }
    }
}
