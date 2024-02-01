package com.quickbase.devint.demo.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Profile("!test")
@Configuration
@EnableJpaRepositories(basePackages = "com.quickbase.devint.demo.adapter.db")
@EntityScan(basePackages = "com.quickbase.devint.demo.adapter.db")
public class DbConfig {
}
