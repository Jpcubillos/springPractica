package com.reference.servlet;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public final class ServletHtml {
    private ServletHtml() {
    }

    public static PrintWriter start(HttpServletResponse response, String title) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>" + escape(title) + "</title></head><body>");
        out.println("<h1>" + escape(title) + "</h1>");
        return out;
    }

    public static void end(PrintWriter out) {
        out.println("</body></html>");
    }

    public static void error(HttpServletResponse response, String message, String backUrl) throws IOException {
        PrintWriter out = start(response, "Error");
        out.println("<p style='color:red'>" + escape(message) + "</p>");
        out.println("<p><a href='" + backUrl + "'>Regresar</a></p>");
        end(out);
    }

    public static String escape(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
