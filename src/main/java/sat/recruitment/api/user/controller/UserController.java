package sat.recruitment.api.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import sat.recruitment.api.user.service.UserService;
import sat.recruitment.controller.api.ParamoApi;
import sat.recruitment.controller.model.UserCreationRequest;


@Controller
@Slf4j
public class UserController implements ParamoApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Void> createUsers(UserCreationRequest userRequestPayload) {
         log.info("INICIO ENPOINT {}" ,  "/paramo/v1/user");
         userService.createUser(userRequestPayload);

        return ResponseEntity.ok().build();
    }
}
