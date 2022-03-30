package com.example.streamrouter.service;

import com.example.streamrouter.model.Subject;
import com.example.streamrouter.repositiry.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public Mono<Subject> findById(@NotNull String id) {
        return subjectRepository.findById(id);
    }

    @Override
    public Mono<Subject> create(@NotNull Mono<Subject> subject) {
        return subject.flatMap(subjectRepository::insert);
    }

    @Override
    public Mono<Subject> update(@NotNull String id, @NotNull Mono<Subject> subject) {
        return subjectRepository.findById(id)
                .doOnNext(s -> new Subject(id, s.getRoute()))
                .flatMap(subjectRepository::save);
    }

    @Override
    public Mono<Void> delete(@NotNull String id) {
        return subjectRepository.deleteById(id);
    }
}
