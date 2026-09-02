# Checklist para parcial Spring Context + Servlets

## Presupuesto sugerido para 2 horas

1. 10 min: leer entidades, relaciones y reglas.
2. 15 min: crear modelos con constructores, getters y setters.
3. 15 min: crear repository interfaces.
4. 20 min: crear implementaciones en memoria.
5. 20 min: crear services e inyeccion por constructor.
6. 15 min: implementar reglas de validacion.
7. 10 min: crear `AppConfig`.
8. 5 min: configurar `web.xml`.
9. 20 min: crear servlets basicos.
10. 5 min: ejecutar `mvn clean package`.
11. 5 min: desplegar WAR.
12. 5 min: probar casos validos e invalidos.

## Orden de desarrollo

1. Leer entidades y relaciones.
2. Crear modelos.
3. Crear repository interfaces.
4. Crear implementaciones.
5. Crear services.
6. Implementar reglas.
7. Crear configuracion.
8. Configurar `web.xml`.
9. Crear servlets.
10. Compilar.
11. Desplegar.
12. Ejecutar pruebas validas e invalidas.

## Errores frecuentes

- Usar `javax.servlet` en Tomcat 10.1. Debe ser `jakarta.servlet`.
- Olvidar `provided` en `jakarta.servlet-api`.
- No registrar `ContextLoaderListener`.
- Equivocarse en `@ComponentScan`.
- Usar `getBean(variable.class)` en lugar de `Interface.class`.
- No escribir `return` en metodos delegados.
- Validar null despues de usar `.length()`.
- Lanzar excepcion incluso despues de guardar.
- Comparar solo timestamp sin comparar `parentId`.
- Calcular el periodo contra el primer registro y no contra el ultimo.
- Escribir rutas absolutas sin `request.getContextPath()`.
- No reconstruir el WAR despues de cambiar codigo.
