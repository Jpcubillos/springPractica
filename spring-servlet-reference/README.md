# Spring Servlet Reference

Repositorio de referencia para preparar parciales de Spring Context + Jakarta Servlets con Java 17, Maven, Tomcat 10.1 y Spring Framework 6. No usa Spring Boot, Lombok, base de datos, JPA ni Thymeleaf.

## Arquitectura por capas

Flujo principal:

```text
Servlet de Tomcat -> Service de Spring -> Repository de Spring -> Lista en memoria
```

- `model`: clases simples con atributos, constructores, getters y setters.
- `repository`: interfaces y clases `@Repository` que guardan datos en `List`.
- `service`: reglas de negocio y validaciones. Aqui se lanzan `IllegalArgumentException`.
- `servlet`: entrada HTTP, formularios, parseo de parametros y manejo de errores.
- `config`: configuracion Java del contenedor Spring.

## Bean de Spring vs Servlet de Tomcat

Un bean de Spring es un objeto creado y administrado por Spring: repositories y services viven ahi. Un servlet es creado por Tomcat. Por eso el servlet no recibe constructor injection de Spring directamente; en `init()` pide el `WebApplicationContext` y obtiene el service con `context.getBean(RecordService.class)`.

## Anotaciones clave

- `@Component`: marca una clase generica para que Spring la detecte.
- `@Repository`: marca acceso a datos; aqui son listas en memoria.
- `@Service`: marca reglas de negocio.
- `@Autowired`: pide inyeccion de dependencias. Con un solo constructor puede omitirse.
- `@Primary`: decide una implementacion preferida cuando hay varias.
- `@Qualifier`: elige un bean especifico por nombre.
- `@Configuration`: clase que declara configuracion Spring.
- `@ComponentScan`: paquete donde Spring busca componentes.
- `@Bean`: metodo que crea un bean manualmente.
- `@PostConstruct`: metodo ejecutado despues de crear el bean.
- `@PreDestroy`: metodo ejecutado antes de destruir el bean.
- `@WebServlet`: registra un servlet en Tomcat.

Spring decide que implementar inyectar por tipo. Si solo existe una clase para una interfaz, la usa. Si hay varias, necesitas `@Primary` o `@Qualifier`.

## Conectar servlets con Spring

`web.xml` registra `ContextLoaderListener`, que arranca Spring con `AppConfig`. Cada servlet hace:

```java
WebApplicationContext context = WebApplicationContextUtils
        .getRequiredWebApplicationContext(getServletContext());
recordService = context.getBean(RecordService.class);
```

## Compilar y generar WAR

```powershell
mvn clean package
```

El WAR queda en:

```text
target/spring-servlet-reference.war
```

## Desplegar en Tomcat 10.1

1. Copia `target/spring-servlet-reference.war` en `TOMCAT_HOME/webapps`.
2. Inicia Tomcat con `bin/startup.bat`.
3. Abre `http://localhost:8080/spring-servlet-reference/records`.
4. Si cambias codigo, ejecuta otra vez `mvn clean package` y reemplaza el WAR.

## URLs del ejemplo

- `/records`: lista todos los registros.
- `/records/create`: formulario de creacion.
- `/records/find`: busqueda por id.
- `/records/update?id=1`: actualizacion.
- `/records/delete?id=1`: confirmacion de eliminacion/inactivacion.

## Indice rapido

- Modelos: `src/main/java/com/reference/model`.
- Repositories: `src/main/java/com/reference/repository`.
- Services y validaciones: `src/main/java/com/reference/service`.
- Servlets: `src/main/java/com/reference/servlet`.
- Configuracion Spring: `src/main/java/com/reference/config/AppConfig.java`.
- Configuracion web: `src/main/webapp/WEB-INF/web.xml`.
- Plantillas: `templates`.
- Patrones de validacion: `validation-patterns/index.md`.
- Alternativas de configuracion: `examples/configuration-alternatives.md`.
- Checklist: `exam-checklist.md`.

## Si el enunciado pide X, busca Y

| Enunciado pide | Busca |
|---|---|
| No repetir un campo | `existsBy...` o `findBy...` |
| Buscar el ultimo registro | `findLatestByParentId` |
| Validar entidad relacionada | `findById` + `orElseThrow` |
| Mostrar todos | `findAll` + servlet de lista |
| Alertar al usuario | excepcion en service + `catch` en servlet |
| Persistir entre solicitudes | repositories singleton administrados por Spring |
| Validar rango | comparar contra minimo y maximo |
| Validar capacidad | `countActiveByParentId` |
| No borrar con hijos | `countByParentId` antes de `deleteById` |
| Evitar rutas rotas | `request.getContextPath()` |

## Ruta mental para resolver rapido

1. Crea modelos simples.
2. Define repository interface.
3. Implementa repository con `List`.
4. Crea service con constructor injection.
5. Valida en service, no en servlet.
6. Crea servlet pequeño por accion.
7. Compila antes de desplegar.
