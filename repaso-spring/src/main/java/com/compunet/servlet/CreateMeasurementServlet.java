package com.compunet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.compunet.model.Measurement;
import com.compunet.service.MeasurementService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/measurements/create")
public class CreateMeasurementServlet extends HttpServlet {

    private MeasurementService measurementService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context =
                WebApplicationContextUtils
                        .getRequiredWebApplicationContext(
                                getServletContext()
                        );

        measurementService =
                context.getBean(MeasurementService.class);
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<h1>Registrar medición</h1>");

        out.println("<form method='POST'>");

        out.println("ID medición:");
        out.println("<input type='number' name='id' required><br>");

        out.println("ID dispositivo:");
        out.println("<input type='number' name='deviceId' required><br>");

        out.println("Timestamp:");
        out.println("<input type='number' name='timeStamp' required><br>");

        out.println("Valor:");
        out.println("<input type='number' step='any' name='value' required><br>");

        out.println("<button type='submit'>Guardar</button>");
        out.println("</form>");

        out.println("<a href='"
                + request.getContextPath()
                + "/measurements'>Ver mediciones</a>");
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            int deviceId = Integer.parseInt(
                    request.getParameter("deviceId")
            );

            long timeStamp = Long.parseLong(
                    request.getParameter("timeStamp")
            );

            double value = Double.parseDouble(
                    request.getParameter("value")
            );

            Measurement measurement =
                    new Measurement(deviceId, id, timeStamp, value);

            measurementService.save(measurement);

            response.sendRedirect(
                    request.getContextPath() + "/measurements"
            );

        } catch (NumberFormatException e) {
            out.println("<h2>Error</h2>");
            out.println("<p>Los datos numéricos no son válidos.</p>");
            out.println("<a href='"
                    + request.getContextPath()
                    + "/measurements/create'>Volver</a>");

        } catch (IllegalArgumentException e) {
            out.println("<h2>Error</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("<a href='"
                    + request.getContextPath()
                    + "/measurements/create'>Volver</a>");
        }
    }
}