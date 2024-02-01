package com.quickbase.devint.demo.adapter.db.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.quickbase.devint.demo.adapter.db")
@EntityScan(basePackages = "com.quickbase.devint.demo.adapter.db")
public class IntegrationTestConfig {
}
