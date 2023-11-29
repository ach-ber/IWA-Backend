package com.iwa.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.iwa.gateway.filter.AuthenticationFilter;

@Configuration
public class RouteLocator {
    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    @Bean
    public org.springframework.cloud.gateway.route.RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service-test", r->r.path("/test/**").filters(f -> f.stripPrefix(1).filter(authenticationFilter())).uri("lb://SERVICE-TEST"))
                .route("service-job", r->r.path("/job/**").filters(f -> f.stripPrefix(1).filter(authenticationFilter())).uri("lb://SERVICE-JOB"))
                .route("service-recruiter", r->r.path("/recruiter/**").filters(f -> f.stripPrefix(1).filter(authenticationFilter())).uri("lb://SERVICE-RECRUITER"))
                .route("service-user", r->r.path("/user/**").filters(f -> f.stripPrefix(1).filter(authenticationFilter())).uri("lb://SERVICE-USER"))
                .route("service-ktlanding", r->r.path("/landing/**").filters(f -> f.stripPrefix(1).filter(authenticationFilter())).uri("lb://SERVICE-KTLANDING"))
                .route("service-review", r->r.path("/review/**").filters(f -> f.stripPrefix(1).filter(authenticationFilter())).uri("lb://SERVICE-REVIEW"))
                .build();
    }
}