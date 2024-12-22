package com.medzak.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.server.WebFilter;

@Configuration
@Slf4j
public class TimingConfig {
    @Bean
    public WebFilter responseTimeWebFilter() {
        return (exchange, chain) -> {
            long startTime = System.currentTimeMillis();

            return chain.filter(exchange)
                    .doOnSuccess(v -> {
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;

                        String path = exchange.getRequest().getURI().getPath();
                        HttpMethod method = exchange.getRequest().getMethod();
                        log.info("API Response Time - Path: {}, Method: {}, Duration: {}ms",
                                path, method, duration);
                    })
                    .doOnError(throwable -> {
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;

                        String path = exchange.getRequest().getURI().getPath();
                        HttpMethod method = exchange.getRequest().getMethod();

                        log.error("API Error - Path: {}, Method: {}, Duration: {}ms, Error: {}",
                                path, method, duration, throwable.getMessage());
                    });
        };
    }
}
