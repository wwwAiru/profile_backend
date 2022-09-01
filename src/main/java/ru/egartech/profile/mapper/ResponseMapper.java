package ru.egartech.profile.mapper;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ResponseMapper {

    public <T> ResponseEntity<T> toResponse(T t) {
        return ResponseEntity.of(
                Optional.of(
                        t
                )
        );
    }

}
