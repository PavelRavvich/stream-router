package com.example.streamrouter.router;

import com.example.streamrouter.exceptions.NotFoundException;
import com.example.streamrouter.handler.SubjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class SubjectRouter {

    @Bean
    public RouterFunction<ServerResponse> route(SubjectHandler subjectHandler) {
        return RouterFunctions
                .route(
                        RequestPredicates.GET("/subject/list")
                                .and(accept(MediaType.APPLICATION_JSON)),
                        subjectHandler::getList)
                .andRoute(
                        RequestPredicates.GET("/subject/{id}")
                                .and(accept(MediaType.APPLICATION_JSON)),
                        subjectHandler::getById
                ).andRoute(
                        RequestPredicates.POST("/subject")
                                .and(accept(MediaType.APPLICATION_JSON)),
                        subjectHandler::create
                ).andRoute(
                        RequestPredicates.PUT("/subject/{id}")
                                .and(accept(MediaType.APPLICATION_JSON)),
                        subjectHandler::update
                ).andRoute(
                        RequestPredicates.DELETE("/subject/{id}")
                                .and(accept(MediaType.APPLICATION_JSON)),
                        subjectHandler::delete
                );
    }

    @Bean
    public WebExceptionHandler exceptionHandler() {
        return (ServerWebExchange exchange, Throwable ex) -> {
            if (ex instanceof NotFoundException) {
                exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                return exchange.getResponse().setComplete();
            }
            return Mono.error(ex);
        };
    }
}