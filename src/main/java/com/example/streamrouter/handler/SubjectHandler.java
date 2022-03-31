package com.example.streamrouter.handler;

import com.example.streamrouter.exceptions.BadRequestException;
import com.example.streamrouter.model.Subject;
import com.example.streamrouter.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SubjectHandler {

    private final SubjectService subjectService;

    @NotNull
    public Mono<ServerResponse> findChildrenByParentName(@NotNull ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        subjectService.findByRoutePattern(
                                request.queryParam("name")
                                        .orElseThrow(() ->
                                                new BadRequestException("name param require"))),
                        Subject.class);
    }

    @NotNull
    public Mono<ServerResponse> findOneById(@NotNull ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        subjectService.findById(
                                request.pathVariable("id")
                        ), Subject.class);
    }

    @NotNull
    public Mono<ServerResponse> create(@NotNull ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        subjectService
                                .create(
                                        request.bodyToMono(Subject.class)),
                        Subject.class);
    }

    @NotNull
    public Mono<ServerResponse> updateStatus(@NotNull ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        subjectService
                                .updateStatus(
                                        request.pathVariable("id"),
                                        Subject.Status.valueOf(request.pathVariable("status"))),
                        Subject.class);
    }

}
