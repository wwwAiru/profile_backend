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

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class ResponseMapper {

    private final CustomFieldProperties properties;

    public Profile toProfile(TaskDto task) {
        TextFieldDto egarId = task.customField(properties.getEgarId());
        AttachmentFieldDto avatarField = task.customField(properties.getAvatar());
        TextFieldDto onBoardField = task.customField(properties.getOnboardDate());
        TextFieldDto birthDate = task.customField(properties.getBirthDate());
        DropdownFieldDto gradeField = task.customField(properties.getGrade());
        TextFieldDto whatsappField = task.customField(properties.getWhatsapp());
        TextFieldDto phoneField = task.customField(properties.getPhone());
        TextFieldDto workEmailField = task.customField(properties.getWorkEmail());
        TextFieldDto telegramField = task.customField(properties.getTelegram());
        TextFieldDto skypeField = task.customField(properties.getSkype());
        DropdownFieldDto positionField = task.customField(properties.getPosition());
        DropdownFieldDto locationField = task.customField(properties.getLocation());
        LabelsFieldDto stackField = task.customField(properties.getStack());
        RelationshipFieldDto sickdayField = task.customField(properties.getSickdayRelationship());
        RelationshipFieldDto vacationsField = task.customField(properties.getVacationRelationship());
        RelationshipFieldDto employmentsField = task.customField(properties.getEmploymentsRelationship());
        RelationshipFieldDto suppliesField = task.customField(properties.getSuppliesRelationship());

        Profile profile = new Profile();
        profile.setListId(task.getList().getId());
        profile.setAvatarUrl(avatarField.getUrl());
        profile.setOnboardDate(onBoardField.getValue());
        profile.setBirthDate(birthDate.getValue());
        profile.setEgarExperience(countExperience(onBoardField));
        profile.setGrade(getGrade(gradeField));
        profile.setEgarId(egarId.getValue());
        profile.setPosition(getDropdownOption(positionField));
        profile.setLocation(getDropdownOption(locationField));
        profile.setSkype(skypeField.getValue());
        profile.setTelegram(telegramField.getValue());
        profile.setWorkEmail(workEmailField.getValue());
        profile.setStack(getStack(stackField));
        profile.setWhatsapp(whatsappField.getValue());
        profile.setPhone(phoneField.getValue());
        profile.setSickdays(getLabelsIds(sickdayField, sickdayField.getName()));
        profile.setVacations(getLabelsIds(vacationsField, vacationsField.getName()));
        profile.setEmployments(getLabelsIds(employmentsField, employmentsField.getName()));
        profile.setSupplies(getLabelsIds(suppliesField, suppliesField.getName()));
        return profile;
    }

    private List<String> getLabelsIds(RelationshipFieldDto sickdayField, String fieldName) {
        if (isNull(sickdayField.getValue())) {
            throw new CustomFieldValueNotFoundException(fieldName);
        }
        return sickdayField
                .getValue()
                .stream()
                .map(RelationshipValueDto::getId)
                .toList();
    }

    private Experience countExperience(TextFieldDto dateField) {
        if (isNull(dateField.getValue())) {
            throw new CustomFieldValueNotFoundException(dateField.getName());
        }
        Experience experience = new Experience();
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
        if (isNull(dropdownField.getValue())) {
            throw new CustomFieldValueNotFoundException(dropdownField.getName());
        }
        return String.valueOf(dropdownField.getValue().getName());
    }

    private ResponseDropdownOption getDropdownOption(DropdownFieldDto dropdownField) {
        if (isNull(dropdownField.getValue())) {
            throw new CustomFieldValueNotFoundException(dropdownField.getName());
        }
        ResponseDropdownOption dropdownOption = new ResponseDropdownOption();
        dropdownOption.setFieldId(dropdownField.getId());
        dropdownOption.setName(dropdownField.getValue().getName());
        dropdownOption.setIndex(dropdownField.getValue().getOrderIndex());
        return dropdownOption;
    }

    private List<String> getStack(LabelsFieldDto labelsField) {
        if (isNull(labelsField.getValue())) {
            throw new CustomFieldValueNotFoundException(labelsField.getName());
        }
        return labelsField.getValue()
                .stream()
                .map(LabelOptionDto::getLabel)
                .collect(Collectors.toList());
    }
}
