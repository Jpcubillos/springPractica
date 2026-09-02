# Plantilla Find Servlet

Reemplaza: `Entity`, `entityService`, ruta y campos.

```java
@WebServlet("/entities/find")
public class FindEntityServlet extends HttpServlet {
    private EntityService entityService;

    public void init() {
        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        entityService = context.getBean(EntityService.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Entity entity = entityService.findRequiredById(id);
            response.getWriter().println(entity.getName());
        } catch (NumberFormatException | IllegalArgumentException ex) {
            response.getWriter().println(ex.getMessage());
        }
    }
}
```
