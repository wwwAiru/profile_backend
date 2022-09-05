package ru.egartech.profile.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.egartech.profile.config.CustomFieldProperties;
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

    private final CustomFieldProperties properties;
    private final TaskMapper taskMapper;

    public <T> ResponseEntity<T> toResponse(T t) {
        return ResponseEntity.of(
                Optional.of(t)
        );
    }

    public Profile toProfile(TaskDto task) {
        Profile profile = new Profile();

        TextFieldDto egarId = task.customField(properties.EGAR_ID);
        AttachmentFieldDto avatarField = task.customField(properties.AVATAR);
        DateFieldDto onBoardField = task.customField(properties.ONBOARD_DATE);
        DateFieldDto birthDate = task.customField(properties.BIRTH_DATE);
        DropdownFieldDto gradeField = task.customField(properties.GRADE);
        EmailFieldDto workEmailField = task.customField(properties.WORK_EMAIL);
        TextFieldDto telegramField = task.customField(properties.TELEGRAM);
        TextFieldDto skypeField = task.customField(properties.SKYPE);
        DropdownFieldDto positionField = task.customField(properties.POSITION);
        LabelsFieldDto stackField = task.customField(properties.STACK);

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
