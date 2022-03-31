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

    /**
     "name": "node3",
     "parentId": "62445e1f5ea3d56b3fbbcde6"


     "id": "62445e1f5ea3d56b3fbbcde6",
     "name": "node3",
     "status": "OK | WARN | STOP",
     "route": "node1.node2.node3.*",
     "parentId": "62445e1f5ea3d56b3fbbcde6",
     "createdDate": "2022-03-30T16:41:51.608",
     "updatedDate": "2022-03-30T16:41:51.608"
     */
    @Override
    public Mono<Subject> create(@NotNull Mono<Subject> subject) {
        return subject
                .flatMap(insert -> findById(insert.getParentId()))
                .flatMap(parent ->
                        subject.doOnNext(child -> {
                            child.setParentId(parent.getId());
                            child.setRoute(Utils.buildRoute(child, parent));
                            child.setCreatedDate(LocalDateTime.now());
                        }))
                .flatMap(subjectRepository::insert);
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
