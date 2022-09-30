package ru.egartech.profile.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.egartech.profile.config.CustomFieldProperties;
import ru.egartech.profile.model.Experience;
import ru.egartech.profile.model.Profile;
import ru.egartech.profile.model.ResponseDropdownOption;
import ru.egartech.sdk.dto.task.deserialization.TaskDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.attachment.AttachmentFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.dropdown.DropdownFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.label.LabelOptionDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.label.LabelsFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.relationship.RelationshipFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.relationship.RelationshipValueDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.text.TextFieldDto;
import ru.egartech.sdk.exception.customfield.CustomFieldValueNotFoundException;

import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResponseMapper {

    private final CustomFieldProperties properties;

    public Profile toProfile(TaskDto task) {
        Profile profile = new Profile();

        TextFieldDto egarId = task.customField(properties.EGAR_ID);
        AttachmentFieldDto avatarField = task.customField(properties.AVATAR);
        TextFieldDto onBoardField = task.customField(properties.ONBOARD_DATE);
        TextFieldDto birthDate = task.customField(properties.BIRTH_DATE);
        DropdownFieldDto gradeField = task.customField(properties.GRADE);
        TextFieldDto whatsappField = task.customField(properties.WHATSAPP);
        TextFieldDto phoneField = task.customField(properties.PHONE);
        TextFieldDto workEmailField = task.customField(properties.WORK_EMAIL);
        TextFieldDto telegramField = task.customField(properties.TELEGRAM);
        TextFieldDto skypeField = task.customField(properties.SKYPE);
        DropdownFieldDto positionField = task.customField(properties.POSITION);
        DropdownFieldDto locationField = task.customField(properties.LOCATION);
        LabelsFieldDto stackField = task.customField(properties.STACK);
        RelationshipFieldDto sickdayField = task.customField(properties.SICKDAY_RELATIONSHIP);
        RelationshipFieldDto vacationsField = task.customField(properties.VACATION_RELATIONSHIP);
        RelationshipFieldDto employmentsField = task.customField(properties.EMPLOYMENTS_RELATIONSHIP);
        RelationshipFieldDto suppliesField = task.customField(properties.SUPPLIES_RELATIONSHIP);

        profile.setListId(task.getList().getId());
        profile.setAvatarUrl(avatarField.getUrl());
        profile.setOnboardDate(onBoardField.getValue());
        profile.setBirthDate(birthDate.getValue());
        profile.setEgarExperience(countExperience(onBoardField));
        profile.setGrade(getGrade(gradeField));
        profile.setEgarId(egarId.getValue());
        profile.setPosition(getPosition(positionField));
        profile.setLocation(getLocation(locationField));
        profile.setSkype(skypeField.getValue());
        profile.setTelegram(telegramField.getValue());
        profile.setWorkEmail(workEmailField.getValue());
        profile.setStack(getStack(stackField));
        profile.setWhatsapp(whatsappField.getValue());
        profile.setPhone(phoneField.getValue());
        profile.setSickdays(getLabelsIds(sickdayField, "Больничные"));
        profile.setVacations(getLabelsIds(vacationsField, "Отпуска"));
        profile.setEmployments(getLabelsIds(employmentsField, "Занятость"));
        profile.setSupplies(getLabelsIds(suppliesField, "Обеспечение"));

        return profile;
    }

    private List<String> getLabelsIds(RelationshipFieldDto sickdayField, String fieldName) {
        if (sickdayField.getValue() == null) throw new CustomFieldValueNotFoundException(fieldName);

        return sickdayField
                .getValue()
                .stream()
                .map(RelationshipValueDto::getId)
                .toList();
    }

    private Experience countExperience(TextFieldDto dateField) {
        Experience experience = new Experience();

        if (dateField.getValue() == null) throw new CustomFieldValueNotFoundException("Дата Выхода");

        Instant onBoard = Instant.ofEpochMilli(Long.parseLong(dateField.getValue()));
        Instant now = Instant.ofEpochMilli(System.currentTimeMillis());
        Period period = Period.between(
                onBoard.atZone(ZoneId.of("UTC")).toLocalDate(),
                now.atZone(ZoneId.of("UTC")).toLocalDate());
        experience.setYears(period.getYears());
        experience.setMonths(period.getMonths());
        return experience;
    }

    private String getGrade(DropdownFieldDto dropdownField) {
        if (dropdownField.getValue() == null) throw new CustomFieldValueNotFoundException("Грейд");
        return String.valueOf(dropdownField.getValue().getName());
    }

    private ResponseDropdownOption getPosition(DropdownFieldDto dropdownField) {
        if (dropdownField.getValue() == null) throw new CustomFieldValueNotFoundException("Специализация основная");

        ResponseDropdownOption dropdownOption = new ResponseDropdownOption();
        dropdownOption.setFieldId(properties.POSITION);
        dropdownOption.setName(dropdownField.getValue().getName());
        dropdownOption.setIndex(dropdownField.getValue().getOrderIndex());
        return dropdownOption;
    }

    private ResponseDropdownOption getLocation(DropdownFieldDto dropdownField) {
        if (dropdownField.getValue() == null) throw new CustomFieldValueNotFoundException("Локация");

        ResponseDropdownOption dropdownOption = new ResponseDropdownOption();
        dropdownOption.setFieldId(properties.LOCATION);
        dropdownOption.setName(dropdownField.getValue().getName());
        dropdownOption.setIndex(dropdownField.getValue().getOrderIndex());
        return dropdownOption;
    }

    private List<String> getStack(LabelsFieldDto labelsField) {
        if (labelsField.getValue() == null) throw new CustomFieldValueNotFoundException("Инструмент тех-ий");

        return labelsField.getValue()
                .stream()
                .map(LabelOptionDto::getLabel)
                .collect(Collectors.toList());
    }
}
