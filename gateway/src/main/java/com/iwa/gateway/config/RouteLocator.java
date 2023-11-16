package com.iwa.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.iwa.gateway.filter.AuthenticationFilter;

@Configuration
public class RouteLocator {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public org.springframework.cloud.gateway.route.RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service-test", r->r.path("/test/**").filters(f -> f.stripPrefix(1).filter(filter)).uri("lb://SERVICE-TEST"))
                .route("service-job", r->r.path("/job/**").filters(f -> f.stripPrefix(1).filter(filter)).uri("lb://SERVICE-JOB"))
                .route("service-recruiter", r->r.path("/recruiter/**").filters(f -> f.stripPrefix(1).filter(filter)).uri("lb://SERVICE-RECRUITER"))
                .build();
    }
}