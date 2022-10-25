package ru.egartech.profile.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "fields")
public class CustomFieldProperties {
    private String egarId;
    private String avatar;
    private String birthDate;
    private String onboardDate;
    private String grade;
    private String workEmail;
    private String telegram;
    private String skype;
    private String position;
    private String location;
    private String stack;
    private String whatsapp;
    private String phone;
    private String sickdayRelationship;
    private String vacationRelationship;
    private String employmentsRelationship;
    private String suppliesRelationship;
}
