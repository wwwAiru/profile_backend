package ru.egartech.profile.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "fields")
public class CustomFieldProperties {
    public String EGAR_ID;
    public String AVATAR;
    public String BIRTH_DATE;
    public String ONBOARD_DATE;
    public String GRADE;
    public String WORK_EMAIL;
    public String TELEGRAM;
    public String SKYPE;
    public String POSITION;
    public String LOCATION;
    public String STACK;
    public String WHATSAPP;
    public String PHONE;
    public String SICKDAY_RELATIONSHIP;
    public String VACATION_RELATIONSHIP;
    public String EMPLOYMENTS_RELATIONSHIP;
    public String SUPPLIES_RELATIONSHIP;
}
