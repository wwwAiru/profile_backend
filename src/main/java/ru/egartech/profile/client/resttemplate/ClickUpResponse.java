package ru.egartech.profile.client.resttemplate;

import lombok.Data;
import ru.egartech.profile.model.task.Task;

import java.util.List;

@Data
public class ClickUpResponse {

    private List<Task> tasks;

}
