package ru.egartech.profile.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.egartech.profile.config.BasicAuthFeignConfig;
import ru.egartech.profile.model.task.Task;

@FeignClient(name = "clickup", configuration = BasicAuthFeignConfig.class, url = "https://api.clickup.com/api/v2")
public interface ClickUpClient {

    @RequestMapping(method = RequestMethod.GET, value = "/task/{id}")
    Task getTask(@PathVariable("id") String id);

}
