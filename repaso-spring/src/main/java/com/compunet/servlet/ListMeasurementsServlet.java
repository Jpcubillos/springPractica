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

@WebServlet("/measurements")
public class ListMeasurementsServlet extends HttpServlet {

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

        out.println("<h1>Mediciones</h1>");
        out.println("<ul>");

        for (Measurement measurement : measurementService.findAll()) {
            out.println(
                    "<li>ID: " + measurement.getId()
                    + " | Dispositivo: " + measurement.getDeviceId()
                    + " | Timestamp: " + measurement.getTimeStamp()
                    + " | Valor: " + measurement.getValue()
                    + "</li>"
            );
        }

        out.println("</ul>");

        out.println(
                "<a href='" + request.getContextPath()
                + "/measurements/create'>Nueva medición</a>"
        );
    }
}