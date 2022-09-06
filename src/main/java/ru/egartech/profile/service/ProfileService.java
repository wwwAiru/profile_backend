package ru.egartech.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.egartech.profile.api.ProfileApiDelegate;
import ru.egartech.profile.config.client.EgarIdFieldFactory;
import ru.egartech.profile.config.client.resttemplate.ClickUpTaskClient;
import ru.egartech.profile.mapper.ResponseMapper;
import ru.egartech.profile.model.Profile;
import ru.egartech.taskmapper.TaskMapper;

@Service
@RequiredArgsConstructor
public class ProfileService implements ProfileApiDelegate {

    private final ClickUpTaskClient clickUpTaskClient;

    private final EgarIdFieldFactory egarIdFieldFactory;

    private final TaskMapper taskMapper;
    private final ResponseMapper resMapper;

    @Value("${lists.dev}")
    private String devListId;

    @Override
    public ResponseEntity<Profile> profileEgarIdGet(String egarId) {
        String json = clickUpTaskClient.getTaskByListIdAndEgarId(devListId, egarIdFieldFactory.of(egarId));

        return resMapper.toResponse(
                resMapper.toProfile(
                        taskMapper.mapList(json).get()
                )
        );
    }

}
