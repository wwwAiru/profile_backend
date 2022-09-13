package ru.egartech.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.egartech.profile.api.ProfileApiDelegate;
import ru.egartech.profile.config.CustomFieldProperties;
import ru.egartech.profile.error.exception.MultipleTasksByEgarIdException;
import ru.egartech.profile.mapper.ResponseMapper;
import ru.egartech.profile.model.Profile;
import ru.egartech.sdk.api.ListTaskClient;
import ru.egartech.sdk.dto.task.CustomFieldRequest;
import ru.egartech.sdk.dto.task.TasksDto;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProfileService implements ProfileApiDelegate {

    private final ListTaskClient client;
    private final ResponseMapper resMapper;
    private final CustomFieldProperties fieldProperties;


    @Override
    public ResponseEntity<Profile> profileEgarIdGet(String egarId) {
        List<TasksDto> tasks = client.getTasksByCustomFields(
                CustomFieldRequest
                        .create()
                        .setFieldId(fieldProperties.EGAR_ID)
                        .setValue(egarId)
        );

        if (tasks.size() > 1) {
            throw new MultipleTasksByEgarIdException(egarId);
        }

        return resMapper.toResponse(
                resMapper.toProfile(
                        Objects.requireNonNull(
                                CollectionUtils.firstElement(tasks)
                        ).getFirstTask()
                )
        );
    }

}
