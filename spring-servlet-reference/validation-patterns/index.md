# Guia de patrones de validacion

Cada patron indica el problema, el codigo minimo, nombres a adaptar y un error frecuente.

## 1. Buscar por ID

Problema: obtener una entidad o cortar el flujo si no existe.

```java
Parent parent = parentRepository.findById(parentId)
        .orElseThrow(() -> new IllegalArgumentException("No existe el Parent"));
```

Adapta: `Parent`, `parentRepository`, `parentId`.  
Evita: usar `.get()` sobre `Optional` sin validar.

## 2. Detectar duplicados

Problema: no repetir id, codigo, timestamp u otro campo unico.

```java
if (entityRepository.existsByCode(entity.getCode())) {
    throw new IllegalArgumentException("Codigo duplicado");
}
```

Adapta: `existsByCode`, `getCode`.  
Evita: validar duplicado despues de guardar.

## 3. Comparar dos atributos

Problema: validar minimo y maximo, inicio y fin, o dos campos relacionados.

```java
if (parent.getMinimumValue() > parent.getMaximumValue()) {
    throw new IllegalArgumentException("El minimo no puede superar el maximo");
}
```

Adapta: nombres de campos.  
Evita: comparar strings con `==`.

## 4. Encontrar el registro mas reciente

Problema: comparar el registro nuevo contra el ultimo existente.

```java
Optional<Record> latest = recordRepository.findLatestByParentId(parentId);
```

Adapta: `Record`, `parentId`.  
Evita: usar el primer registro si la regla pide el ultimo.

## 5. Calcular diferencias de tiempo

Problema: medir distancia entre timestamp actual y anterior.

```java
long difference = current.getTimestamp() - previous.getTimestamp();
if (difference <= 0) throw new IllegalArgumentException("Debe ser posterior");
```

Adapta: campos de fecha o timestamp.  
Evita: restar en orden contrario.

## 6. Validar periodo +/- tolerancia

Problema: aceptar un intervalo dentro de margen.

```java
long min = parent.getPeriod() - parent.getTolerance();
long max = parent.getPeriod() + parent.getTolerance();
if (difference < min || difference > max) {
    throw new IllegalArgumentException("Periodo fuera de tolerancia");
}
```

Adapta: `period`, `tolerance`, `difference`.  
Evita: exigir igualdad exacta si el enunciado da tolerancia.

## 7. Contar registros relacionados

Problema: saber cuantos hijos tiene una entidad padre.

```java
long total = recordRepository.countByParentId(parent.getId());
```

Adapta: repositorio hijo y campo FK.  
Evita: contar todos los registros sin filtrar por padre.

## 8. Validar capacidad

Problema: no superar el maximo permitido.

```java
if (recordRepository.countActiveByParentId(parent.getId()) >= parent.getMaximumCapacity()) {
    throw new IllegalArgumentException("Capacidad maxima alcanzada");
}
```

Adapta: `countActiveByParentId`, `maximumCapacity`.  
Evita: contar inactivos si solo importan activos.

## 9. Validar rangos

Problema: asegurar que un numero este entre minimo y maximo.

```java
if (value < parent.getMinimumValue() || value > parent.getMaximumValue()) {
    throw new IllegalArgumentException("Valor fuera de rango");
}
```

Adapta: `value`, minimo y maximo.  
Evita: usar `&&` cuando debe fallar por estar debajo o encima.

## 10. Validar strings

Problema: campo obligatorio con longitud.

```java
if (text == null) throw new IllegalArgumentException("Texto requerido");
if (text.trim().isEmpty()) throw new IllegalArgumentException("Texto vacio");
if (text.length() > 100) throw new IllegalArgumentException("Texto muy largo");
```

Adapta: campo y limites.  
Evita: llamar `.length()` antes de validar null.

## 11. Validar relaciones

Problema: no guardar un hijo con padre inexistente.

```java
Parent parent = parentService.findRequiredById(record.getParentId());
```

Adapta: service relacionado.  
Evita: saltarte el service si la arquitectura exige capas.

## 12. Validar estados

Problema: separar activos e inactivos.

```java
if (!parent.isActive()) {
    throw new IllegalArgumentException("Entidad inactiva");
}
```

Adapta: `isActive`.  
Evita: permitir operaciones sobre inactivos si el enunciado lo prohibe.

## 13. Validar eliminacion

Problema: impedir borrar padres con hijos.

```java
if (recordRepository.countByParentId(parentId) > 0) {
    throw new IllegalArgumentException("No se puede eliminar: tiene relacionados");
}
```

Adapta: repositorio hijo.  
Evita: borrar primero y validar despues.

## 14. Lanzar excepciones

Problema: cortar el flujo con mensaje claro.

```java
throw new IllegalArgumentException("Mensaje entendible para el usuario");
```

Adapta: mensaje.  
Evita: mensajes tecnicos como `NullPointerException`.

## 15. Capturar errores en servlet

Problema: mostrar errores sin romper la respuesta.

```java
try {
    Long id = Long.parseLong(request.getParameter("id"));
    service.deleteById(id);
    response.sendRedirect(request.getContextPath() + "/entities");
} catch (NumberFormatException ex) {
    response.getWriter().println("Numero invalido");
} catch (IllegalArgumentException ex) {
    response.getWriter().println(ex.getMessage());
}
```

Adapta: ruta, service y accion.  
Evita: redireccionar con rutas absolutas sin `contextPath`.
