package com.example.streamrouter.repositiry;

import com.example.streamrouter.model.Subject;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends ReactiveMongoRepository<Subject, String> {
}
