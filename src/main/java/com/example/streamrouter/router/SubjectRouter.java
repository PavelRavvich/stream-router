package com.example.streamrouter.router;

import com.example.streamrouter.handler.SubjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

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
                        subjectHandler::getSubject
                ).andRoute(
                        RequestPredicates.POST("/subject")
                                .and(accept(MediaType.APPLICATION_JSON)),
                        subjectHandler::createSubject
                ).andRoute(
                        RequestPredicates.PUT("/subject/{id}")
                                .and(accept(MediaType.APPLICATION_JSON)),
                        subjectHandler::updateSubject
                ).andRoute(
                        RequestPredicates.DELETE("/subject/{id}")
                                .and(accept(MediaType.APPLICATION_JSON)),
                        subjectHandler::deleteSubject
                );
    }
}