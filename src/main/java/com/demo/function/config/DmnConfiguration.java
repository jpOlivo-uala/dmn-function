package com.demo.function.config;

import java.io.IOException;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class DmnConfiguration {

    @Value("classpath:dmn/loyalty.dmn")
    Resource resourceFile;

    @Bean
    public DmnEngine createDmnEngine() {
        // create default DMN engine configuration
        DmnEngineConfiguration configuration = DmnEngineConfiguration
                .createDefaultDmnEngineConfiguration();

        // build a new DMN engine
        return configuration.buildEngine();
    }

    
    @Bean(name = "voucherDmn")
    public DmnDecision createVoucherDecision(DmnEngine dmnEngine) throws IOException {
        return dmnEngine.parseDecision("voucher", resourceFile.getInputStream());
    }
}