package com.casablanca.SpringConnect.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentServiceConfiguration {

    @Bean
    RentService rentService() {
        return new RentService();
    }
}