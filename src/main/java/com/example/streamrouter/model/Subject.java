package com.example.streamrouter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "subjects")
public class Subject {
    @Id
    private String id;

    private Status status;

    private String name;

    private String route;

    private String parentId;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public enum Status {
        OPEN("OPEN"), WARN("WARN"), CLOSE("CLOSE");

        Status(String v) {
        }
    }
}
