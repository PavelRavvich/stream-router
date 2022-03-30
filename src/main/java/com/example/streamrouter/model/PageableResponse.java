package com.example.streamrouter.model;

import java.util.Collection;

public record PageableResponse<T> (Long total, Collection<T> data) {
}
