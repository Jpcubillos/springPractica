# Plantilla List Servlet

Reemplaza: `Entity`, `entityService`, ruta y columnas.

```java
@WebServlet("/entities")
public class ListEntityServlet extends HttpServlet {
    private EntityService entityService;

    public void init() {
        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        entityService = context.getBean(EntityService.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        PrintWriter out = response.getWriter();
        out.println("<a href='" + contextPath + "/entities/create'>Crear</a>");
        for (Entity entity : entityService.findAll()) {
            out.println("<p>" + entity.getId() + " - " + entity.getName() + "</p>");
        }
    }
}
```
