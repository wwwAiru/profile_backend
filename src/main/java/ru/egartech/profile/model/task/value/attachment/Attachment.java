package ru.egartech.profile.model.task.value.attachment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachment {

    private String id;
    private String url;

}
