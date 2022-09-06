package ru.egartech.profile.config.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.egartech.taskmapper.dto.task.TaskDto;

@FeignClient(name = "clickup", configuration = BasicAuthFeignConfig.class, url = "https://api.clickup.com/api/v2")
public interface ClickUpClient {

    @RequestMapping(method = RequestMethod.GET, value = "/task/{id}")
    TaskDto getTask(@PathVariable("id") String id);

}
