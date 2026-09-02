package com.reference.servlet;

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

@WebServlet("/records/delete")
public class DeleteRecordServlet extends HttpServlet {
    private RecordService recordService;

    // Obtiene desde Spring el RecordService que usara el servlet.
    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        recordService = context.getBean(RecordService.class);
    }

    // Muestra una confirmacion antes de eliminar o inactivar el Record.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            PrintWriter out = ServletHtml.start(response, "Eliminar Record");
            out.println("<p>Confirma eliminar/inactivar el Record " + id + "?</p>");
            out.println("<form method='post' action='" + contextPath + "/records/delete'>");
            out.println("<input type='hidden' name='id' value='" + id + "'>");
            out.println("<button type='submit'>Eliminar</button>");
            out.println("</form>");
            out.println("<p><a href='" + contextPath + "/records'>Regresar</a></p>");
            ServletHtml.end(out);
        } catch (NumberFormatException ex) {
            ServletHtml.error(response, "El id debe ser numerico", contextPath + "/records");
        }
    }

    // Lee el id confirmado, llama al service y redirige al listado.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            recordService.deleteById(id);
            response.sendRedirect(contextPath + "/records");
        } catch (NumberFormatException ex) {
            ServletHtml.error(response, "El id debe ser numerico", contextPath + "/records");
        } catch (IllegalArgumentException ex) {
            ServletHtml.error(response, ex.getMessage(), contextPath + "/records");
        }
    }
}
