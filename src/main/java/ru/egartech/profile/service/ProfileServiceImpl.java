package ru.egartech.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.egartech.profile.config.CustomFieldProperties;
import ru.egartech.profile.error.exception.MultipleTasksByEgarIdException;
import ru.egartech.profile.mapper.ResponseMapper;
import ru.egartech.profile.model.Profile;
import ru.egartech.profile.model.ResponseDropdownOption;
import ru.egartech.profile.model.ResponseLabelsOption;
import ru.egartech.sdk.api.CustomFieldClient;
import ru.egartech.sdk.api.ListTaskClient;
import ru.egartech.sdk.dto.task.deserialization.TasksDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.FieldsDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.dropdown.DropdownFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.dropdown.DropdownTypeConfig;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.label.LabelTypeConfig;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.label.LabelsFieldDto;
import ru.egartech.sdk.dto.task.serialization.customfield.request.CustomFieldRequest;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ListTaskClient client;
    private final CustomFieldClient customFieldClient;
    private final ResponseMapper resMapper;
    private final CustomFieldProperties fieldProperties;

    @Override
    public Profile profileIdEgarIdGet(String egarId) {
        List<TasksDto> tasks = client.getTasksByCustomFields(
                CustomFieldRequest
                        .create()
                        .setFieldId(fieldProperties.EGAR_ID)
                        .setValue(egarId));

        if (tasks.size() > 1) {
            throw new MultipleTasksByEgarIdException(egarId);
        }

        return resMapper.toProfile(
                Objects.requireNonNull(
                        CollectionUtils.firstElement(tasks)
                ).getFirstTask()
        );
    }

    @Override
    public List<ResponseDropdownOption> profileDropdownListListIdFieldFieldIdGet(
            Integer listId,
            String fieldId
    ) {
        FieldsDto accessibleCustomFields = customFieldClient.getAccessibleCustomFields(listId);

        DropdownFieldDto dropdowns = accessibleCustomFields.customField(fieldId);
        DropdownTypeConfig dropdownTypeConfig = dropdowns.getDropdownTypeConfig();

        return dropdownTypeConfig
                .getLabelOptions()
                .stream()
                .map(d -> ResponseDropdownOption
                        .builder()
                        .index(d.getOrderIndex())
                        .name(d.getName())
                        .build())
                .toList();
    }

    @Override
    public List<ResponseLabelsOption> profileLabelsListListIdFieldFieldIdGet(Integer listId, String fieldId) {
        FieldsDto accessibleCustomFields = customFieldClient.getAccessibleCustomFields(listId);

        LabelsFieldDto labelsFieldDto = accessibleCustomFields.customField(fieldId);
        LabelTypeConfig labelTypeConfig = labelsFieldDto.getLabelTypeConfig();

        return labelTypeConfig
                .getLabelOptionDtos()
                .stream()
                .map(o -> ResponseLabelsOption
                        .builder()
                        .name(o.getLabel())
                        .id(o.getId())
                        .build()
                )
                .toList();
    }
}
