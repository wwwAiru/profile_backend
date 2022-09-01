package ru.egartech.profile.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-01T14:21:08.684389800+03:00[Europe/Moscow]")
@Controller
@RequestMapping("${openapi.profileBackend.base-path:}")
public class ProfileApiController implements ProfileApi {

    private final ProfileApiDelegate delegate;

    public ProfileApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) ProfileApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ProfileApiDelegate() {});
    }

    @Override
    public ProfileApiDelegate getDelegate() {
        return delegate;
    }

}
