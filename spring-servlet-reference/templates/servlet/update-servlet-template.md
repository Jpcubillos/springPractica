# Plantilla Update Servlet

Reemplaza: `Entity`, `entityService`, ruta y parametros.

```java
@WebServlet("/entities/update")
public class UpdateEntityServlet extends HttpServlet {
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
            String name = request.getParameter("name");
            boolean active = Boolean.parseBoolean(request.getParameter("active"));
            entityService.update(new Entity(id, name, active));
            response.sendRedirect(contextPath + "/entities");
        } catch (NumberFormatException | IllegalArgumentException ex) {
            response.getWriter().println(ex.getMessage());
        }
    }
}
```
