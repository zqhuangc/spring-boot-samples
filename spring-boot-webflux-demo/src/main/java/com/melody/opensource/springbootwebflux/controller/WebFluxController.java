package com.melody.opensource.springbootwebflux.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zqhuangc
 */
@RestController
public class WebFluxController {

    @GetMapping("/webflux0")
    public Mono<String> testMono(){
        return Mono.just("webflux0");
    }

    @GetMapping("/webflux1")
    public Flux<String> testFlux(){
        return Flux.just("webflux1");
    }

}
