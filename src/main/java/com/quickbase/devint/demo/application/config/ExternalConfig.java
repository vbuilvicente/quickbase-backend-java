package com.quickbase.devint.demo.application.config;

import com.quickbase.devint.demo.adapter.remote.ConcreteStatService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalConfig {

    @Bean
    public ConcreteStatService concreteStatService() {
        return new ConcreteStatService();
    }
}
