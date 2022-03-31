package com.example.streamrouter.service;

import com.example.streamrouter.model.Subject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubjectService {
    Flux<Subject> findByRoutePattern(String routePattern);
    Mono<Subject> findById(String id);
    Mono<Subject> create(Mono<Subject> subject);
    Mono<Subject> updateStatus(String id, Subject.Status status);
}
