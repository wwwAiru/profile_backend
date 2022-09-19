package ru.egartech.profile.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import ru.egartech.profile.config.CustomFieldProperties;
import ru.egartech.profile.model.Experience;
import ru.egartech.profile.model.Profile;
import ru.egartech.profile.model.ResponseDropdownOption;
import ru.egartech.sdk.dto.task.deserialization.TaskDto;
import ru.egartech.sdk.dto.task.deserialization.TasksDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.label.LabelsFieldDto;
import ru.egartech.sdk.dto.task.deserialization.customfield.field.relationship.RelationshipFieldDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ResponseMapperTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ResponseMapper responseMapper;

    @Autowired
    private CustomFieldProperties properties;

    @Test
    @SneakyThrows
    @DisplayName("Verify that response mapper correctly mapping TaskDto")
    public void testResponseMapper() {
        TasksDto response = mapper.readValue(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX.concat("clickup_response_tasks.json")), TasksDto.class);
        TaskDto task = response.getFirstTask();
        Profile profile = responseMapper.toProfile(task);
        assertThat(profile).isEqualTo(Profile
                .builder()
                .avatarUrl("https://t4597045.p.clickup-attachments.com/t4597045/c9372130-1753-4f77-b79d-f7b8c0a3b18a/%D0%B8%D0%B7%D0%BE%D0%B1%D1%80%D0%B0%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5_2022-09-13_165406813.png")
                .birthDate("519177600000")
                .onboardDate("1618016400000")
                .position(ResponseDropdownOption.builder().fieldId("33064c03-b21f-4f93-b74d-b2ef4b081208").name("Разработчик").index(0).build())
                .egarExperience(Experience.builder().years(1).months(5).build())
                .egarId("username")
                .employments(List.of("2z4kcma", "2z4g3d7"))
                .listId(180311895)
                .grade("Middle (3)")
                .skype("username")
                .whatsapp("https://wa.me/15551234567")
                .workEmail("username@egartech.ru")
                .telegram("@username")
                .stack(List.of("Java / Java 11"))
                .vacations(List.of("2wmahab"))
                .sickdays(List.of("2wmagcr"))
                .build()
        );
        assertThat(profile).hasNoNullFieldsOrProperties();
    }

    @Test
    @SneakyThrows
    @DisplayName("Verify that response mapper correctly mapping empty TaskDto")
    public void testEmptyFieldsOnTask() {
        TasksDto response = mapper.readValue(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX.concat("clickup_response_tasks.json")), TasksDto.class);
        TaskDto task = response.getFirstTask();
        task.getCustomFields()
                .entrySet()
                .stream()
                .filter(entry ->
                        except(entry, properties.EGAR_ID) &&
                        except(entry, properties.GRADE) &&
                        except(entry, properties.POSITION) &&
                        except(entry, properties.ONBOARD_DATE) &&
                        except(entry, properties.STACK) &&
                        except(entry, properties.SICKDAY_RELATIONSHIP) &&
                        except(entry, properties.VACATION_RELATIONSHIP) &&
                        except(entry, properties.EMPLOYMENTS_RELATIONSHIP))
                .forEach(entry -> entry.getValue().setValue(null));
        task.customField(properties.STACK, LabelsFieldDto.class).setValue(new ArrayList<>());
        task.customField(properties.SICKDAY_RELATIONSHIP, RelationshipFieldDto.class).setValue(new ArrayList<>());
        task.customField(properties.VACATION_RELATIONSHIP, RelationshipFieldDto.class).setValue(new ArrayList<>());
        task.customField(properties.EMPLOYMENTS_RELATIONSHIP, RelationshipFieldDto.class).setValue(new ArrayList<>());
        responseMapper.toProfile(task);
    }

    private boolean except(Map.Entry<String, ?> entry, String fieldId) {
        return !entry.getKey().equals(fieldId);
    }
}