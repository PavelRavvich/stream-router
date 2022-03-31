package com.example.streamrouter.service;

import com.example.streamrouter.exceptions.NotFoundException;
import com.example.streamrouter.model.Subject;
import com.example.streamrouter.repositiry.SubjectRepository;
import com.example.streamrouter.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public Flux<Subject> findByRoutePattern(@NotNull String routePattern) {
        return subjectRepository.findByRouteContains(routePattern);
    }

    @Override
    public Mono<Subject> findById(@NotNull String id) {
        return subjectRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(id)));
    }

    @Override
    public Mono<Subject> create(@NotNull Mono<Subject> subject) {
        return subject
                .flatMap(child -> subjectRepository
                        .findById(child.getParentId())
                        .flatMap(parent -> {
                            child.setParentId(parent.getId());
                            child.setStatus(Subject.Status.OPEN);
                            child.setCreatedDate(LocalDateTime.now());
                            child.setRoute(Utils.buildRoute(child, parent));
                            return subjectRepository.insert(child);
                        }));
    }

    @Override
    public Mono<Subject> updateStatus(@NotNull String id, @NotNull Subject.Status status) {
        return findById(id)
                .doOnNext(origin -> {
                    origin.setStatus(status);
                    origin.setUpdatedDate(LocalDateTime.now());
                })
                .flatMap(subjectRepository::save);
    }
}
