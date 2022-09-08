package ru.egartech.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.egartech.profile.api.ProfileApiDelegate;
import ru.egartech.profile.config.CustomFieldProperties;
import ru.egartech.profile.mapper.ResponseMapper;
import ru.egartech.profile.model.Profile;
import ru.egartech.taskmapper.api.CustomFieldsRequest;
import ru.egartech.taskmapper.api.TaskClient;
import ru.egartech.taskmapper.dto.task.TasksDto;

@Service
@RequiredArgsConstructor
public class ProfileService implements ProfileApiDelegate {

    private final TaskClient client;

    private final ResponseMapper resMapper;

    private final CustomFieldProperties properties;

    @Value("${lists.dev}")
    private String devListId;

    @Override
    public ResponseEntity<Profile> profileEgarIdGet(String egarId) {
        TasksDto tasks = client.getTasksByCustomField(
                devListId,
                CustomFieldsRequest.builder()
                        .fieldId(properties.EGAR_ID)
                        .operator("=")
                        .value(egarId)
                        .build()
        );

        return resMapper.toResponse(
                resMapper.toProfile(tasks.get())
        );
    }

}
