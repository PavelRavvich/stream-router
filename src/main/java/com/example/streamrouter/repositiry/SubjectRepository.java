package com.example.streamrouter.repositiry;

import com.example.streamrouter.model.Subject;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SubjectRepository extends ReactiveMongoRepository<Subject, String> {
    Flux<Subject> findByRouteContains(String pattern);
}
