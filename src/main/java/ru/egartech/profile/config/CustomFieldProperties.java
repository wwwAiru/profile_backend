package ru.egartech.profile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomFieldProperties {

    public final String EGAR_ID;
    public final String AVATAR;
    public final String BIRTH_DATE;
    public final String ONBOARD_DATE;
    public final String GRADE;
    public final String WORK_EMAIL;
    public final String TELEGRAM;
    public final String SKYPE;
    public final String POSITION;
    public final String STACK;
    public final String WHATSAPP;

    public CustomFieldProperties(
            @Value("${fields.egar_id}")
            String EGAR_ID,

            @Value("${fields.avatar}")
            String AVATAR,

            @Value("${fields.birthdate}")
            String BIRTH_DATE,

            @Value("${fields.onboard_date}")
            String onboard_date,

            @Value("${fields.grade}")
            String GRADE,

            @Value("${fields.work_email}")
            String WORK_EMAIL,

            @Value("${fields.telegram}")
            String TELEGRAM,

            @Value("${fields.skype}")
            String SKYPE,

            @Value("${fields.position}")
            String POSITION,

            @Value("${fields.stack}")
            String STACK,

            @Value("${fields.whatsapp}")
            String whatsapp
    ) {
        this.EGAR_ID = EGAR_ID;
        this.AVATAR = AVATAR;
        this.BIRTH_DATE = BIRTH_DATE;
        this.ONBOARD_DATE = onboard_date;
        this.GRADE = GRADE;
        this.WORK_EMAIL = WORK_EMAIL;
        this.TELEGRAM = TELEGRAM;
        this.SKYPE = SKYPE;
        this.POSITION = POSITION;
        this.STACK = STACK;
        this.WHATSAPP = whatsapp;
    }

}
