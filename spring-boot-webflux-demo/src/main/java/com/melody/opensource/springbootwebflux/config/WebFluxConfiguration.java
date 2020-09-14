package com.melody.opensource.springbootwebflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author zqhuangc
 */

@Configuration
public class WebFluxConfiguration {

    @Bean
    public RouterFunction routerFunction(){
        return RouterFunctions.route(RequestPredicates.GET("/webflux"),
                request -> {
                    Optional<String> message = request.queryParam("message");
                    return ServerResponse.ok().body(Mono.just(message.get()), String.class);
                });

    }

    @Bean
    public RouterFunction testRouterFunction(){
        return RouterFunctions.route(RequestPredicates.POST("/test"),
                request -> ServerResponse.ok().body(request.bodyToMono(String.class).map(String::toString), String.class)
        );

    }
}
