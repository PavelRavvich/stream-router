package com.example.streamrouter.handler;

import com.example.streamrouter.model.PageableRequest;
import com.example.streamrouter.model.PageableResponse;
import com.example.streamrouter.model.Subject;
import com.example.streamrouter.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SubjectHandler {

    private final SubjectService subjectService;

    @NotNull
    public Mono<ServerResponse> getList(@NotNull ServerRequest request) {
        Mono<PageableRequest> pageableRequest = request.bodyToMono(PageableRequest.class);

        List<Subject> list = List.of();
        Mono<PageableResponse<Subject>> data = Mono.just(
                new PageableResponse<>(1L, list));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, PageableResponse.class);
    }

    @NotNull
    public Mono<ServerResponse> getById(@NotNull ServerRequest request) {
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
    public Mono<ServerResponse> update(@NotNull ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        subjectService
                                .update(
                                        request.pathVariable("id"),
                                        request.bodyToMono(Subject.class)),
                        Subject.class);
    }

    @NotNull
    public Mono<ServerResponse> delete(@NotNull ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        subjectService.delete(request.pathVariable("id")),
                        Map.class);
    }
}
