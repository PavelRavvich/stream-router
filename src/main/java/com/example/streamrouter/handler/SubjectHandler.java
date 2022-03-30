package com.example.streamrouter.handler;

import com.example.streamrouter.model.PageableRequest;
import com.example.streamrouter.model.PageableResponse;
import com.example.streamrouter.model.Subject;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class SubjectHandler {

    @NotNull
    public Mono<ServerResponse> getList(@NotNull ServerRequest request) {
        Mono<PageableRequest> pageableRequest = request.bodyToMono(PageableRequest.class);

        List<Subject> list = List.of(
                new Subject(UUID.randomUUID(), "test.route.1"),
                new Subject(UUID.randomUUID(), "test.route.2"),
                new Subject(UUID.randomUUID(), "test.route.3"));
        Mono<PageableResponse<Subject>> data = Mono.just(
                new PageableResponse<>(1L, list));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, PageableResponse.class);
    }

    @NotNull
    public Mono<ServerResponse> getSubject(@NotNull ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Subject> data = Mono
                .just(new Subject(UUID.fromString(id), "test.route.*"));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, Subject.class);
    }

    @NotNull
    public Mono<ServerResponse> createSubject(@NotNull ServerRequest request) {
        Mono<Subject> data = request.bodyToMono(Subject.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, Subject.class);
    }

    @NotNull
    public Mono<ServerResponse> updateSubject(@NotNull ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Subject> data = request.bodyToMono(Subject.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, Subject.class);
    }

    @NotNull
    public Mono<ServerResponse> deleteSubject(@NotNull ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        Mono<Map<String, UUID>> data = Mono.just(Map.of("id", id));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, Map.class);
    }
}
