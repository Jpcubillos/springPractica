# Plantilla Create Servlet

Reemplaza: `Entity`, `entityService`, ruta y parametros.

```java
@WebServlet("/entities/create")
public class CreateEntityServlet extends HttpServlet {
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
            entityService.save(new Entity(id, name, active));
            response.sendRedirect(contextPath + "/entities");
        } catch (NumberFormatException ex) {
            response.getWriter().println("Numero invalido");
        } catch (IllegalArgumentException ex) {
            response.getWriter().println(ex.getMessage());
        }
    }
}
```
