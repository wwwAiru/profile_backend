package ru.egartech.profile.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "fields")
public class CustomFieldProperties {
    public String egarId;
    public String avatar;
    public String birth_date;
    public String onboard_date;
    public String grade;
    public String workEmail;
    public String telegram;
    public String skype;
    public String position;
    public String location;
    public String stack;
    public String whatsapp;
    public String phone;
    public String sickdayRelationship;
    public String vacationRelationship;
    public String employmentsRelationship;
    public String suppliesRelationship;
}
