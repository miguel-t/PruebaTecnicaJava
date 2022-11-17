package sat.recruitment.api.user.service;


import sat.recruitment.controller.model.UserCreationRequest;

public interface UserService {

    void createUser(UserCreationRequest userCreationDto);
}
