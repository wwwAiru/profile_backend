package ru.egartech.profile.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Profile
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-01T14:21:08.684389800+03:00[Europe/Moscow]")
public class Profile   {
  @JsonProperty("egar_id")
  private String egarId;

  @JsonProperty("position")
  private String position;

  @JsonProperty("grade")
  private String grade;

  @JsonProperty("avatar_url")
  private String avatarUrl;

  @JsonProperty("onboard_date")
  private String onboardDate;

  @JsonProperty("birth_date")
  private String birthDate;

  @JsonProperty("egar_experience")
  private Integer egarExperience;

  @JsonProperty("work_email")
  private String workEmail;

  @JsonProperty("telegram")
  private String telegram;

  @JsonProperty("skype")
  private String skype;

  public Profile egarId(String egarId) {
    this.egarId = egarId;
    return this;
  }

  /**
   * Get egarId
   * @return egarId
  */
  @ApiModelProperty(example = "MPetrova", value = "")


  public String getEgarId() {
    return egarId;
  }

  public void setEgarId(String egarId) {
    this.egarId = egarId;
  }

  public Profile position(String position) {
    this.position = position;
    return this;
  }

  /**
   * Get position
   * @return position
  */
  @ApiModelProperty(example = "Разработчик", value = "")


  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public Profile grade(String grade) {
    this.grade = grade;
    return this;
  }

  /**
   * Get grade
   * @return grade
  */
  @ApiModelProperty(example = "Middle", value = "")


  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public Profile avatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
    return this;
  }

  /**
   * Get avatarUrl
   * @return avatarUrl
  */
  @ApiModelProperty(example = "https://t4597045.p.clickup-attachments.com/t4597045/e19a8d64-ba5b-47ce-84e6-dde62092d5f1_large.png", value = "")


  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public Profile onboardDate(String onboardDate) {
    this.onboardDate = onboardDate;
    return this;
  }

  /**
   * Get onboardDate
   * @return onboardDate
  */
  @ApiModelProperty(example = "04.09.2021", value = "")


  public String getOnboardDate() {
    return onboardDate;
  }

  public void setOnboardDate(String onboardDate) {
    this.onboardDate = onboardDate;
  }

  public Profile birthDate(String birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  /**
   * Get birthDate
   * @return birthDate
  */
  @ApiModelProperty(example = "01.01.1981", value = "")


  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public Profile egarExperience(Integer egarExperience) {
    this.egarExperience = egarExperience;
    return this;
  }

  /**
   * Get egarExperience
   * @return egarExperience
  */
  @ApiModelProperty(example = "1", value = "")


  public Integer getEgarExperience() {
    return egarExperience;
  }

  public void setEgarExperience(Integer egarExperience) {
    this.egarExperience = egarExperience;
  }

  public Profile workEmail(String workEmail) {
    this.workEmail = workEmail;
    return this;
  }

  /**
   * Get workEmail
   * @return workEmail
  */
  @ApiModelProperty(example = "pupkin@egar.com", value = "")


  public String getWorkEmail() {
    return workEmail;
  }

  public void setWorkEmail(String workEmail) {
    this.workEmail = workEmail;
  }

  public Profile telegram(String telegram) {
    this.telegram = telegram;
    return this;
  }

  /**
   * Get telegram
   * @return telegram
  */
  @ApiModelProperty(example = "@petr", value = "")


  public String getTelegram() {
    return telegram;
  }

  public void setTelegram(String telegram) {
    this.telegram = telegram;
  }

  public Profile skype(String skype) {
    this.skype = skype;
    return this;
  }

  /**
   * Get skype
   * @return skype
  */
  @ApiModelProperty(example = "pupkin@egar.com", value = "")


  public String getSkype() {
    return skype;
  }

  public void setSkype(String skype) {
    this.skype = skype;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profile profile = (Profile) o;
    return Objects.equals(this.egarId, profile.egarId) &&
        Objects.equals(this.position, profile.position) &&
        Objects.equals(this.grade, profile.grade) &&
        Objects.equals(this.avatarUrl, profile.avatarUrl) &&
        Objects.equals(this.onboardDate, profile.onboardDate) &&
        Objects.equals(this.birthDate, profile.birthDate) &&
        Objects.equals(this.egarExperience, profile.egarExperience) &&
        Objects.equals(this.workEmail, profile.workEmail) &&
        Objects.equals(this.telegram, profile.telegram) &&
        Objects.equals(this.skype, profile.skype);
  }

  @Override
  public int hashCode() {
    return Objects.hash(egarId, position, grade, avatarUrl, onboardDate, birthDate, egarExperience, workEmail, telegram, skype);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Profile {\n");
    
    sb.append("    egarId: ").append(toIndentedString(egarId)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    grade: ").append(toIndentedString(grade)).append("\n");
    sb.append("    avatarUrl: ").append(toIndentedString(avatarUrl)).append("\n");
    sb.append("    onboardDate: ").append(toIndentedString(onboardDate)).append("\n");
    sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
    sb.append("    egarExperience: ").append(toIndentedString(egarExperience)).append("\n");
    sb.append("    workEmail: ").append(toIndentedString(workEmail)).append("\n");
    sb.append("    telegram: ").append(toIndentedString(telegram)).append("\n");
    sb.append("    skype: ").append(toIndentedString(skype)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

