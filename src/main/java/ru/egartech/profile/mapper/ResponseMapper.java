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
import ru.egartech.profile.model.task.value.date.DateField;
import ru.egartech.profile.model.task.value.text.TextField;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class ResponseMapper {

    public <T> ResponseEntity<T> toResponse(T t) {
        return ResponseEntity.of(
                Optional.of(t)
        );
    }

    public Profile toProfile(Task task) {
        Profile profile = new Profile();

        AttachmentField avatarField = (AttachmentField) task.customField(f -> f instanceof AttachmentField, "Аватар");
        DateField onBoardField = (DateField) task.customField(f -> f instanceof DateField && f.getName().contains("Дата выхода"), "Дата выхода");
        DateField birthDate = (DateField) task.customField(f -> f instanceof DateField && f.getName().contains("Дата рождения"), "Дата рождения");
        DropdownField gradeField = (DropdownField) task.customField(f -> f instanceof DropdownField && f.getName().contains("Грейд"), "Грейд");
        TextField workEmailField = (TextField) task.customField(f -> f instanceof TextField && f.getName().contains("Email рабочий"), "Email рабочий");
        DropdownField positionField = (DropdownField) task.customField(f -> f instanceof DropdownField && f.getName().contains("Специализация"), "Специализация");

        profile.setAvatarUrl(avatarField.getUrl());
        profile.setOnboardDate(onBoardField.getValue());
        profile.setBirthDate(birthDate.getValue());
        profile.setEgarExperience(countExperience(onBoardField));
        profile.setGrade(getGrade(gradeField));
        profile.setEgarId(getEgarId(workEmailField));
        profile.setPosition(getPosition(positionField));
        profile.setSkype(""); //TODO: Добавить Скайп в модель
        profile.setTelegram(""); // TODO: Добавить Телегу в модель
        profile.setWorkEmail(workEmailField.getName());

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
        return String.valueOf(dropdownField.getValue().getOrderIndex());
    }

    private String getPosition(DropdownField dropdownField) {
        return dropdownField.getValue().getName();
    }

    private String getEgarId(TextField textField) {
        int end = textField.getValue().lastIndexOf("@");
        return textField.getValue().substring(0, end);
    }
}
