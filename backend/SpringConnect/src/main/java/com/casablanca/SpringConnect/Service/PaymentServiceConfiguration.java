package com.casablanca.SpringConnect.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentServiceConfiguration {

    @Bean
    PaymentService paymentService() {
        return new PaymentService();
    }
}
