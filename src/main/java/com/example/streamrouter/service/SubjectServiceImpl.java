package com.example.streamrouter.service;

import com.example.streamrouter.exceptions.NotFoundException;
import com.example.streamrouter.model.Subject;
import com.example.streamrouter.repositiry.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public Mono<Subject> findById(@NotNull String id) {
        return subjectRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(id)));
    }

    @Override
    public Mono<Subject> create(@NotNull Mono<Subject> subject) {
        return subject
                .doOnNext(s -> s.setCreatedDate(LocalDateTime.now()))
                .flatMap(subjectRepository::insert);
    }

    @Override
    public Mono<Subject> update(@NotNull String id, @NotNull Mono<Subject> subject) {
        return subjectRepository
                .findById(id)
                .switchIfEmpty(
                        Mono.error(new NotFoundException(id)))
                .doOnNext(s ->
                        new Subject(id,
                                s.getRoute(),
                                s.getCreatedDate(),
                                LocalDateTime.now())
                ).flatMap(subjectRepository::save);
    }

    @Override
    public Mono<Void> delete(@NotNull String id) {
        return subjectRepository.deleteById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(id)));
    }
}
