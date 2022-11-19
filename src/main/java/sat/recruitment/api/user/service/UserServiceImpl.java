package sat.recruitment.api.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sat.recruitment.api.core.exception.ApiServerException;
import sat.recruitment.api.user.dto.UserCreationResponseDTO;
import sat.recruitment.api.user.entity.User;
import sat.recruitment.api.user.enums.CalculateStrategyNameEnum;
import sat.recruitment.api.user.repository.UserRepository;
import sat.recruitment.controller.model.UserCreationRequest;

import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CalculateMoneyStrategyFactory calculateMoneyStrategyFactory;

    @Override
    public UserCreationResponseDTO createUser(UserCreationRequest userCreationDto) {
        this.validateUser(userCreationDto);

        CalculateStrategyNameEnum calculateStrategyName = CalculateStrategyNameEnum.getByCode(userCreationDto.getUserType());
        CalculateMoneyStrategy calculateMoneyStrategy = calculateMoneyStrategyFactory.findStrategy(calculateStrategyName);

        Double amount = userCreationDto.getMoney();
        if(Objects.nonNull(calculateMoneyStrategy)){
            userCreationDto.setMoney(calculateMoneyStrategy.calculateMoney(amount));
            log.info("nuevo importe " + userCreationDto.getMoney());
        }

        User newUser = userRepository.save(this.toUser(userCreationDto));
        return toUserResponseDto(newUser);
    }


    private void validateUser(UserCreationRequest userCreationDto) {
        if(userRepository.existsByEmail(userCreationDto.getEmail())){
            throw new ApiServerException("The mail exists",HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByUserName(userCreationDto.getName())){
            throw new ApiServerException("The userName exists",HttpStatus.BAD_REQUEST);
        }
    }

    private UserCreationResponseDTO toUserResponseDto(User newUser) {
        UserCreationResponseDTO newUserDto = new UserCreationResponseDTO();
        newUserDto.setUserName(newUser.getUserName());
        newUserDto.setEmail(newUser.getEmail());
        newUserDto.setAddress(newUser.getAddress());
        newUserDto.setPhone(newUser.getPhone());
        newUserDto.setUserType(newUser.getUserType());
        newUserDto.setMoney(newUser.getMoney());
        newUserDto.setUserId(newUser.getUserId());
        return newUserDto;

    }

    private User toUser(UserCreationRequest userCreationDto) {
        User user = new User();
        user.setUserName(userCreationDto.getName());
        user.setEmail(userCreationDto.getEmail());
        user.setAddress(userCreationDto.getAddress());
        user.setPhone(userCreationDto.getPhone());
        user.setUserType(userCreationDto.getUserType());
        user.setMoney(userCreationDto.getMoney());

        return user;
    }
}
