package ru.egartech.profile.api;

import ru.egartech.profile.model.Profile;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link ProfileApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-01T14:21:08.684389800+03:00[Europe/Moscow]")
public interface ProfileApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /profile/{egarId}
     * Получение профиля сотрудника по Egar ID (Где взять: [egarId]@egartech.ru)
     *
     * @param egarId Egar ID сотрудника (required)
     * @return Профиль сотрудника без Sickday (status code 200)
     *         or Неверный Egar ID (status code 400)
     *         or Сотрудник не найден (status code 404)
     * @see ProfileApi#profileEgarIdGet
     */
    default ResponseEntity<Profile> profileEgarIdGet(String egarId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"work_email\" : \"pupkin@egar.com\", \"skype\" : \"pupkin@egar.com\", \"egar_id\" : \"MPetrova\", \"avatar_url\" : \"https://t4597045.p.clickup-attachments.com/t4597045/e19a8d64-ba5b-47ce-84e6-dde62092d5f1_large.png\", \"egar_experience\" : 1, \"grade\" : \"Middle\", \"birth_date\" : \"01.01.1981\", \"telegram\" : \"@petr\", \"position\" : \"Разработчик\", \"onboard_date\" : \"04.09.2021\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
