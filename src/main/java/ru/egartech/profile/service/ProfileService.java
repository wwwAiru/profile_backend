package ru.egartech.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.egartech.profile.api.ProfileApiDelegate;
import ru.egartech.profile.config.CustomFieldProperties;
import ru.egartech.profile.error.exception.MultipleTasksByEgarIdException;
import ru.egartech.profile.mapper.ResponseMapper;
import ru.egartech.profile.model.DropdownOption;
import ru.egartech.profile.model.Profile;
import ru.egartech.sdk.api.CustomFieldClient;
import ru.egartech.sdk.api.ListTaskClient;
import ru.egartech.sdk.dto.task.CustomFieldRequest;
import ru.egartech.sdk.dto.task.FieldsDto;
import ru.egartech.sdk.dto.task.TasksDto;
import ru.egartech.sdk.dto.task.customfield.field.dropdown.DropdownFieldDto;
import ru.egartech.sdk.dto.task.customfield.field.dropdown.DropdownTypeConfig;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProfileService implements ProfileApiDelegate {

    private final ListTaskClient client;
    private final CustomFieldClient customFieldClient;
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

    @Override
    public ResponseEntity<List<DropdownOption>> profileDropdownListIdFieldIdGet(
            Integer listId,
            String fieldId
    ) {
        FieldsDto accessibleCustomFields = customFieldClient.getAccessibleCustomFields(listId);

        DropdownFieldDto dropdowns = accessibleCustomFields.customField(fieldId);
        DropdownTypeConfig dropdownTypeConfig = dropdowns.getDropdownTypeConfig();

        return resMapper.toResponse(
                dropdownTypeConfig
                        .getLabelOptions()
                        .stream()
                        .map(d -> {
                            DropdownOption dropdownOption = new DropdownOption();
                            dropdownOption.setIndex(d.getOrderIndex());
                            dropdownOption.setName(d.getName());
                            return dropdownOption;
                        })
                        .toList()
        );
    }
}
