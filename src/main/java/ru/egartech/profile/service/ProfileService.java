package ru.egartech.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.egartech.profile.api.ProfileApiDelegate;
import ru.egartech.profile.client.ClickUpClient;
import ru.egartech.profile.mapper.ResponseMapper;
import ru.egartech.profile.model.Profile;

@Service
@RequiredArgsConstructor
public class ProfileService implements ProfileApiDelegate {

    private final ClickUpClient clickUpClient;

    private final ResponseMapper resMapper;

    @Override
    public ResponseEntity<Profile> profileEgarIdGet(String egarId) {

        clickUpClient.getTask("25cf2w3")
                .getCustomFields()
//                .stream()
//                .map(CustomField::getValue)
                .forEach(System.out::println);

        return resMapper.toResponse(new Profile());
    }

//    @Override
//    public ResponseEntity<Projects> profileEgarIdProjectsGet(String egarId) {
//        return resMapper.toResponse(new Projects());
//    }
//
//    @Override
//    public ResponseEntity<Sickday> profileEgarIdSickdayGet(String egarId) {
//        return resMapper.toResponse(new Sickday());
//    }
}
