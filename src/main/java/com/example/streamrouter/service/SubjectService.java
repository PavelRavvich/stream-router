package com.example.streamrouter.service;

import com.example.streamrouter.model.Subject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubjectService {
    Flux<Subject> findAll();
    Mono<Subject> findById(String id);
    Mono<Subject> create(Mono<Subject> subject);
    Mono<Subject> update(String id, Mono<Subject> subject);
    Mono<Void> delete(String id);
}
