package sat.recruitment.api.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
