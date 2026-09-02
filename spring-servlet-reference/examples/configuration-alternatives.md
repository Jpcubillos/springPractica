# Alternativas de configuracion Spring

No actives estas alternativas al mismo tiempo que `@ComponentScan` si crean los mismos beans. Usa una sola estrategia para evitar dos implementaciones candidatas de la misma interfaz.

## 1. Configuracion actual: anotaciones

```java
@Configuration
@ComponentScan(basePackages = "com.reference")
public class AppConfig {
}
```

Uso recomendado en parcial: rapido, limpio y suficiente cuando tus clases tienen `@Repository`, `@Service` o `@Component`.

## 2. Configuracion manual con `@Bean`

```java
@Configuration
public class ManualConfig {
    @Bean
    public ParentRepository parentRepository() {
        return new InMemoryParentRepository();
    }

    @Bean
    public RecordRepository recordRepository() {
        return new InMemoryRecordRepository();
    }

    @Bean
    public ParentService parentService(ParentRepository parentRepository, RecordRepository recordRepository) {
        return new ParentServiceImpl(parentRepository, recordRepository);
    }

    @Bean
    public RecordService recordService(RecordRepository recordRepository, ParentService parentService) {
        return new RecordServiceImpl(recordRepository, parentService);
    }
}
```

Uso recomendado: cuando el profesor pide demostrar explicitamente como se construyen los objetos.

## 3. XML clasico

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="parentRepository" class="com.reference.repository.InMemoryParentRepository"/>
    <bean id="recordRepository" class="com.reference.repository.InMemoryRecordRepository"/>

    <bean id="parentService" class="com.reference.service.ParentServiceImpl">
        <constructor-arg ref="parentRepository"/>
        <constructor-arg ref="recordRepository"/>
    </bean>

    <bean id="recordService" class="com.reference.service.RecordServiceImpl">
        <constructor-arg ref="recordRepository"/>
        <constructor-arg ref="parentService"/>
    </bean>
</beans>
```

Uso recomendado: solo si el parcial exige XML o si quieres comparar con la configuracion moderna.
