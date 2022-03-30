package com.example.streamrouter.router;

import com.example.streamrouter.handler.SubjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

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
}