package ru.egartech.profile.mapper;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.egartech.profile.client.EgarIdField;
import ru.egartech.profile.model.Experience;
import ru.egartech.profile.model.Profile;
import ru.egartech.profile.model.task.Task;
import ru.egartech.profile.model.task.value.CustomField;
import ru.egartech.profile.model.task.value.attachment.AttachmentField;
import ru.egartech.profile.model.task.value.collection.DropdownField;
import ru.egartech.profile.model.task.value.collection.LabelOption;
import ru.egartech.profile.model.task.value.collection.LabelsField;
import ru.egartech.profile.model.task.value.date.DateField;
import ru.egartech.profile.model.task.value.text.TextField;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class ResponseMapper {

    public <T> ResponseEntity<T> toResponse(T t) {
        return ResponseEntity.of(
                Optional.of(t)
        );
    }

    public Profile toProfile(Task task) {
        Profile profile = new Profile();

        TextField egarId = (TextField) task.customField(f -> f instanceof TextField && f.getName().contains("_egar_id"), "Egar ID");
        AttachmentField avatarField = (AttachmentField) task.customField(f -> f instanceof AttachmentField, "Аватар");
        DateField onBoardField = (DateField) task.customField(f -> f instanceof DateField && f.getName().contains("Дата выхода"), "Дата выхода");
        DateField birthDate = (DateField) task.customField(f -> f instanceof DateField && f.getName().contains("Дата рождения"), "Дата рождения");
        DropdownField gradeField = (DropdownField) task.customField(f -> f instanceof DropdownField && f.getName().equals("040 Грейд"), "Грейд");
        TextField workEmailField = (TextField) task.customField(f -> f instanceof TextField && f.getName().contains("Email рабочий"), "Email рабочий");
        TextField telegramField = (TextField) task.customField(f -> f instanceof TextField && f.getName().contains("Телеграм"), "Телеграмм");
        TextField skypeField = (TextField) task.customField(f -> f instanceof TextField && f.getName().contains("Skype"), "Skype");
        DropdownField positionField = (DropdownField) task.customField(f -> f instanceof DropdownField && f.getName().contains("Специализация основная"), "Специализация");
        LabelsField stackField = (LabelsField) task.customField(f -> f instanceof LabelsField && f.getName().contains("Инструмент тех-ий"), "Инструмент тех-ий");

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

    private Experience countExperience(DateField dateField) {
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

    private String getGrade(DropdownField dropdownField) {
        return String.valueOf(dropdownField.getValue().getName());
    }

    private String getPosition(DropdownField dropdownField) {
        return dropdownField.getValue().getName();
    }

    private List<String> getStack(LabelsField labelsField) {
        return labelsField.getValue()
                .stream()
                .map(LabelOption::getLabel)
                .collect(Collectors.toList());
    }
}
