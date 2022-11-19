package sat.recruitment.api.user.service;


import sat.recruitment.api.user.dto.UserCreationResponseDTO;
import sat.recruitment.controller.model.UserCreationRequest;

public interface UserService {

    UserCreationResponseDTO createUser(UserCreationRequest userCreationDto);
}
