package com.example.astrademostep3;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AstraDriverConfig {

    @Bean
    public CqlSession cqlSession() {

        //this uses the configuration in the application.conf
        return CqlSession.builder().build();
    }
}