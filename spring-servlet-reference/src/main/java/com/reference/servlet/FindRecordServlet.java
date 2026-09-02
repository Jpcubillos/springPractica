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

@WebServlet("/records/find")
public class FindRecordServlet extends HttpServlet {
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
        PrintWriter out = ServletHtml.start(response, "Buscar Record");
        out.println("<form method='post' action='" + contextPath + "/records/find'>");
        out.println("<p>Id: <input name='id'></p>");
        out.println("<button type='submit'>Buscar</button>");
        out.println("</form>");
        out.println("<p><a href='" + contextPath + "/records'>Regresar</a></p>");
        ServletHtml.end(out);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Record record = recordService.findRequiredById(id);
            PrintWriter out = ServletHtml.start(response, "Record encontrado");
            out.println("<ul>");
            out.println("<li>Id: " + record.getId() + "</li>");
            out.println("<li>Parent id: " + record.getParentId() + "</li>");
            out.println("<li>Timestamp: " + record.getTimestamp() + "</li>");
            out.println("<li>Valor: " + record.getValue() + "</li>");
            out.println("<li>Descripcion: " + ServletHtml.escape(record.getDescription()) + "</li>");
            out.println("<li>Activo: " + record.isActive() + "</li>");
            out.println("</ul>");
            out.println("<p><a href='" + contextPath + "/records'>Regresar</a></p>");
            ServletHtml.end(out);
        } catch (NumberFormatException ex) {
            ServletHtml.error(response, "El id debe ser numerico", contextPath + "/records/find");
        } catch (IllegalArgumentException ex) {
            ServletHtml.error(response, ex.getMessage(), contextPath + "/records/find");
        }
    }
}
