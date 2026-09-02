# Plantilla Delete Servlet

Reemplaza: `Entity`, `entityService` y ruta.

```java
@WebServlet("/entities/delete")
public class DeleteEntityServlet extends HttpServlet {
    private EntityService entityService;

    public void init() {
        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        entityService = context.getBean(EntityService.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            entityService.deleteById(id);
            response.sendRedirect(contextPath + "/entities");
        } catch (NumberFormatException | IllegalArgumentException ex) {
            response.getWriter().println(ex.getMessage());
        }
    }
}
```
