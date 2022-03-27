package com.common.config;

import lombok.Data;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DslConfig {

    private String url;
    private String username;
    private String password;

    public DSLContext dslContext(){
        DSLContext create = null;
        try (Connection conn = (Connection) DriverManager.getConnection(url, username, password)) {
            create = DSL.using(conn, SQLDialect.MYSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return create;
    }


}
