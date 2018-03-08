# spring-starter
Projeto inicial springboot


## JBoss standalone datasorce
`<datasource jndi-name="java:jboss/datasources/CapesDS" pool-name="CapesDS" enabled="true" use-java-context="true">
    <connection-url>jdbc:h2:tcp://localhost/~/capesDB;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
    <driver>h2</driver>
    <security>
        <user-name>sa</user-name>
        <password></password>
    </security>
</datasource>`

## Para acessar o banco H2
executar o jar da pasta 
˜/wildfly/modules/system/layers/base/com/h2database/h2/main

## URL do swagger gerado, apos subir o servidor ( atenção para a porta )
http://localhost:9080/wscapes/swagger-ui.html

## URL do JSON do swagger gerado, apos subir o servidor ( atenção para a porta )
http://localhost:9080/wscapes/v2/api-docs


* **Inserir as tabelas de pessoa, usuario e usuario role, a senha pode ser gerada pela classe SecurityConfig**
