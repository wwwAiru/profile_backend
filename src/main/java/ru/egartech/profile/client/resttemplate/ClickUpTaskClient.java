package ru.egartech.profile.client.resttemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.egartech.profile.client.EgarIdField;
import ru.egartech.profile.error.exception.NotFoundException;
import ru.egartech.profile.model.task.Task;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClickUpTaskClient {

    private static final String URL = "https://api.clickup.com/api/v2/list/{list_id}/task?custom_fields={egarId}";

    private final ObjectMapper mapper;
    private final RestTemplate rest;

    @SneakyThrows
    public Task getTaskByListIdAndEgarId(String listId, EgarIdField egarId) {

        String customField = mapper.writeValueAsString(List.of(egarId));
        String url = URL.replace("{list_id}", listId);
        String json = rest.getForObject(url, String.class, customField);

        ClickUpResponse res = mapper.readValue(json, ClickUpResponse.class);

        if (res.getTasks().size() < 1) throw new NotFoundException(egarId.getValue());

//        throw new RuntimeException();

        return res.getTasks()
                .stream()
                .findFirst()
                .orElseThrow();
    }
}
