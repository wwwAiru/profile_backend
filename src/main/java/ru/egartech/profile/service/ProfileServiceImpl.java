package ru.egartech.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.egartech.profile.config.CustomFieldProperties;
import ru.egartech.profile.error.exception.MultipleTasksByEgarIdException;
import ru.egartech.profile.mapper.ResponseMapper;
import ru.egartech.profile.model.Profile;
import ru.egartech.profile.model.ResponseDropdownOption;
import ru.egartech.profile.model.ResponseLabelsOption;
import ru.egartech.sdk.api.CustomFieldClient;
import ru.egartech.sdk.api.ListTaskClient;
import ru.egartech.sdk.dto.task.deserialization.TaskDto;
import ru.egartech.sdk.dto.task.deserialization.TasksDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.FieldsDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.dropdown.DropdownFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.dropdown.DropdownTypeConfig;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.label.LabelTypeConfig;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.label.LabelsFieldDto;
import ru.egartech.sdk.dto.task.serialization.customfield.request.CustomFieldRequest;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ListTaskClient client;
    private final CustomFieldClient customFieldClient;
    private final ResponseMapper mapper;
    private final CustomFieldProperties fieldProperties;
    private final MessageSource messageSource;

    @Override
    public Profile getProfileByEgarId(String egarId) {
        List<TasksDto> tasks = client.getTasksByCustomFields(false,
                CustomFieldRequest
                        .builder()
                        .fieldId(fieldProperties.EGAR_ID)
                        .value(egarId)
                        .build());

        if (tasks.size() > 1) throw new MultipleTasksByEgarIdException(egarId);

        Supplier<NullPointerException> exceptionSupplier =
                () -> new NullPointerException(messageSource.getMessage(
                        "notasksinquery",
                        null,
                        Locale.getDefault()));
        TaskDto exactTask = tasks.stream().findFirst().orElseThrow(exceptionSupplier).getFirstTask();
        return mapper.toProfile(exactTask);
    }

    @Override
    public List<ResponseDropdownOption> getDropdownOptions(Integer listId,
                                                           String fieldId) {
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
    public List<ResponseLabelsOption> getLabelsOptions(Integer listId,
                                                       String fieldId) {
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
                        .build())
                .toList();
    }
}
