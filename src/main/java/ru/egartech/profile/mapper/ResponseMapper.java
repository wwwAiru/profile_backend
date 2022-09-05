package ru.egartech.profile.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.egartech.profile.model.Experience;
import ru.egartech.profile.model.Profile;
import ru.egartech.taskmapper.TaskMapper;
import ru.egartech.taskmapper.dto.task.TaskDto;
import ru.egartech.taskmapper.dto.task.customfield.field.attachment.AttachmentFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.date.DateFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.dropdown.DropdownFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.email.EmailFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.label.LabelOptionDto;
import ru.egartech.taskmapper.dto.task.customfield.field.label.LabelsFieldDto;
import ru.egartech.taskmapper.dto.task.customfield.field.text.TextFieldDto;

import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResponseMapper {

    private final TaskMapper taskMapper;

    public <T> ResponseEntity<T> toResponse(T t) {
        return ResponseEntity.of(
                Optional.of(t)
        );
    }

    public Profile toProfile(TaskDto task) {
        Profile profile = new Profile();

        TextFieldDto egarId = task.customField("836c9684-0c71-4714-aff2-900b0ded0685");
        AttachmentFieldDto avatarField = task.customField("d01ad323-e69b-4413-9d57-256613e62ee0");
        DateFieldDto onBoardField = task.customField("ea8cc2d2-4255-4cf8-b207-0b96ff6e4987");
        DateFieldDto birthDate = task.customField("15b5edd5-7b26-4a2b-9def-e0c211731265");
        DropdownFieldDto gradeField = task.customField("e7e89fd8-c5a6-4ae9-84ff-00bf3292033e");
        EmailFieldDto workEmailField = task.customField("5c6128d2-1c1f-420d-890a-e85afd61b123");
        TextFieldDto telegramField = task.customField("7dcb1177-6071-40c0-83dc-5c1b45cc7a3c");
        TextFieldDto skypeField = task.customField("6f023e63-c487-40cb-8eb3-bb582557e4a0");
        DropdownFieldDto positionField = task.customField("33064c03-b21f-4f93-b74d-b2ef4b081208");
        LabelsFieldDto stackField = task.customField("948c4322-be0e-473a-9f54-52b1cb6d428b");

        profile.setAvatarUrl(avatarField.getUrl());
        profile.setOnboardDate(onBoardField.getValue());
        profile.setBirthDate(birthDate.getValue());
        profile.setEgarExperience(countExperience(onBoardField));
        profile.setGrade(getGrade(gradeField));
        profile.setEgarId(egarId.getValue());
        profile.setPosition(getPosition(positionField));
        profile.setSkype(skypeField.getValue());
        profile.setTelegram(telegramField.getValue());
        profile.setWorkEmail(workEmailField.getValue());
        profile.setStack(getStack(stackField));

        return profile;
    }

    private Experience countExperience(DateFieldDto dateField) {
        Experience experience = new Experience();

        Instant onBoard = Instant.ofEpochMilli(Long.parseLong(dateField.getValue()));
        Instant now = Instant.ofEpochMilli(System.currentTimeMillis());

        Period period = Period.between(

                onBoard.atZone(ZoneId.of("UTC"))
                        .toLocalDate(),

                now.atZone(ZoneId.of("UTC"))
                        .toLocalDate()

        );

        experience.setYears(period.getYears());
        experience.setMonths(period.getMonths());

        return experience;
    }

    private String getGrade(DropdownFieldDto dropdownField) {
        return String.valueOf(dropdownField.getValue().getName());
    }

    private String getPosition(DropdownFieldDto dropdownField) {
        return dropdownField.getValue().getName();
    }

    private List<String> getStack(LabelsFieldDto labelsField) {
        return labelsField.getValue()
                .stream()
                .map(LabelOptionDto::getLabel)
                .collect(Collectors.toList());
    }
}
