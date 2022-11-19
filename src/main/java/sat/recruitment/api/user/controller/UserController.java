package sat.recruitment.api.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import sat.recruitment.api.user.dto.UserCreationResponseDTO;
import sat.recruitment.api.user.service.UserService;
import sat.recruitment.controller.api.ParamoApi;
import sat.recruitment.controller.model.UserCreationRequest;

import java.util.List;


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

    @Override
    public ResponseEntity<List<UserCreationResponseDTO>> getAllUsers() {
        log.info("INICIO GET ENPOINT {}" ,  "/paramo/v1/users");

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
