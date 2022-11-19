package sat.recruitment.api.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sat.recruitment.api.user.entity.User;

@Getter
@Setter
@NoArgsConstructor
public class UserCreationResponseDTO {

    private Long userId;
    private String userName;
    private String email;
    private String address;
    private String phone;
    private String userType;
    private Double money;


    public static UserCreationResponseDTO toUserResponseDto(User newUser) {
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
}
