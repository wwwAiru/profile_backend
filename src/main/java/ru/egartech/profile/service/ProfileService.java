package ru.egartech.profile.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.egartech.profile.api.ProfileApiDelegate;
import ru.egartech.profile.client.EgarIdField;
import ru.egartech.profile.client.feign.ClickUpClient;
import ru.egartech.profile.client.resttemplate.ClickUpTaskClient;
import ru.egartech.profile.mapper.ResponseMapper;
import ru.egartech.profile.model.Profile;
import ru.egartech.taskmapper.TaskMapper;

@Service
@RequiredArgsConstructor
public class ProfileService implements ProfileApiDelegate {

    private final ClickUpClient clickUpClient;
    private final ClickUpTaskClient clickUpTaskClient;

    private final TaskMapper taskMapper;
    private final ResponseMapper resMapper;

    private final ObjectMapper mapper;

    @Override
    public ResponseEntity<Profile> profileEgarIdGet(String egarId) {
        String json = clickUpTaskClient.getTaskByListIdAndEgarId("180311895", new EgarIdField(egarId));

        return resMapper.toResponse(
                resMapper.toProfile(
                        taskMapper.mapList(json).get()
                )
        );
    }

}
