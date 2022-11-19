package sat.recruitment.api.user.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import sat.recruitment.api.core.exception.ApiServerException;
import sat.recruitment.api.user.dto.UserCreationResponseDTO;
import sat.recruitment.api.user.entity.User;
import sat.recruitment.api.user.enums.CalculateStrategyNameEnum;
import sat.recruitment.api.user.repository.UserRepository;
import sat.recruitment.controller.model.UserCreationRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    private CalculateMoneyStrategyFactory strategyFactory;

    @InjectMocks
    UserServiceImpl userService;

    private  User user;
    //User Normal
    private  User normalUser;
    private  UserCreationRequest normalUserRequestDto;

    //user premin
    private  User premiunUser;
    private  UserCreationRequest premiunUserDto;

    //super user
    private  User superUser;
    private  UserCreationRequest superUserDto;

    private  UserCreationRequest userCreationDto;



    @BeforeEach
    public void setup() {
        System.out.println("===  before=======");
        this.user = new User();
        user.setUserId(1L);
        user.setUserType("");
        user.setUserName("matias");
        user.setMoney(0.1);
        user.setPhone("1564127076");
        user.setAddress("av riesta 1434");
        user.setEmail("matias@gmail.com");

        this.userCreationDto = new UserCreationRequest();
        userCreationDto.setUserType("");
        userCreationDto.setName("matias");
        userCreationDto.setMoney(0.1);
        userCreationDto.setPhone("1564127076");
        userCreationDto.setAddress("av riesta 1434");
        userCreationDto.setEmail("matias@gmail.com");

        //user normal
        this.normalUser =  new User();
        normalUser.setUserName("joaquin");
        normalUser.setUserId(2L);
        normalUser.setMoney(100.1);
        normalUser.setPhone("1564127076");
        normalUser.setAddress("av riesta 1434");
        normalUser.setEmail("joaquin@gmail.com");
        normalUser.setUserType(CalculateStrategyNameEnum.NORMAL.getCode());

        this.normalUserRequestDto = new UserCreationRequest();
        normalUserRequestDto.setName("joaquin");
        normalUserRequestDto.setMoney(100.1);
        normalUserRequestDto.setPhone("1564127076");
        normalUserRequestDto.setAddress("av riesta 1434");
        normalUserRequestDto.setEmail("joaquin@gmail.com");
        normalUserRequestDto.setUserType(CalculateStrategyNameEnum.NORMAL.getCode());

        //user premiun
        this.premiunUser = new User();
        premiunUser.setUserId(3L);
        premiunUser.setUserName("userPremiun");
        premiunUser.setMoney(200.1);
        premiunUser.setPhone("1564127076");
        premiunUser.setAddress("av riesta 1434");
        premiunUser.setEmail("userPremiun@gmail.com");
        premiunUser.setUserType(CalculateStrategyNameEnum.PREMIUN.getCode());

        this.premiunUserDto = new UserCreationRequest();
        premiunUserDto.setName("userPremiun");
        premiunUserDto.setMoney(200.1);
        premiunUserDto.setPhone("1564127076");
        premiunUserDto.setAddress("av riesta 1434");
        premiunUserDto.setEmail("userPremiun@gmail.com");
        premiunUserDto.setUserType(CalculateStrategyNameEnum.PREMIUN.getCode());

        //super user ;
        this.superUser = new User();
        superUser.setUserId(4L);
        superUser.setUserName("userSuper");
        superUser.setMoney(300.1);
        superUser.setPhone("1564127076");
        superUser.setAddress("av riesta 1434");
        superUser.setEmail("userSuper@gmail.com");
        superUser.setUserType(CalculateStrategyNameEnum.SUPERUSER.getCode());

        this.superUserDto = new UserCreationRequest();
        superUserDto.setName("userSuper");
        superUserDto.setMoney(300.1);
        superUserDto.setPhone("1564127076");
        superUserDto.setAddress("av riesta 1434");
        superUserDto.setEmail("userSuper@gmail.com");
        superUserDto.setUserType(CalculateStrategyNameEnum.SUPERUSER.getCode());
    }

    @Test
    public void savedUser_Success() {

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(strategyFactory.findStrategy(any(CalculateStrategyNameEnum.class))).thenReturn(new NormalCalculateMoneyStrategy());

        UserCreationResponseDTO newrUserResponseDTO = userService.createUser(userCreationDto);

        assertThat(newrUserResponseDTO.getUserId()).isNotNull();

    }

    @Test
    public void  saveUser_nameExist_Error()  {

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(strategyFactory.findStrategy(any(CalculateStrategyNameEnum.class))).thenReturn(null);
        when(userRepository.existsByUserName(any(String.class))).thenReturn(Boolean.TRUE);


        Throwable exception = Assertions.assertThrows(ApiServerException.class, () -> {
            userService.createUser(userCreationDto);
        });

        Assertions.assertEquals("The userName exists", exception.getMessage());
    }

    @Test
    public void  saveUser_emailExist_Error()  {

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(strategyFactory.findStrategy(any(CalculateStrategyNameEnum.class))).thenReturn(null);
        when(userRepository.existsByEmail(any(String.class))).thenReturn(Boolean.TRUE);


        Throwable exception = Assertions.assertThrows(ApiServerException.class, () -> {
            userService.createUser(userCreationDto);
        });

        Assertions.assertEquals("The mail exists", exception.getMessage());
    }
    @Test
    public void  saveUser_normalType_amount_greater_than_100()  {


        when(userRepository.save(any(User.class))).thenReturn(normalUser);
        when(strategyFactory.findStrategy(any(CalculateStrategyNameEnum.class))).thenReturn(new NormalCalculateMoneyStrategy());
        when(userRepository.existsByEmail(any(String.class))).thenReturn(Boolean.FALSE);
        when(userRepository.existsByUserName(any(String.class))).thenReturn(Boolean.FALSE);

        Double money = normalUserRequestDto.getMoney();

        UserCreationResponseDTO newUser = userService.createUser(normalUserRequestDto);
        assertThat(newUser.getUserId()).isNotNull();

        assertThat(normalUserRequestDto.getMoney()).isGreaterThan(money);
    }

    @Test
    public void  saveUser_premiunType_amount_greater_than_100()  {


        when(userRepository.save(any(User.class))).thenReturn(premiunUser);
        when(strategyFactory.findStrategy(any(CalculateStrategyNameEnum.class))).thenReturn(new PremiunCalculateMoneyStrategy());
        when(userRepository.existsByEmail(any(String.class))).thenReturn(Boolean.FALSE);
        when(userRepository.existsByUserName(any(String.class))).thenReturn(Boolean.FALSE);

        Double money = premiunUserDto.getMoney();

        UserCreationResponseDTO newUser = userService.createUser(premiunUserDto);

        assertThat(newUser.getUserId()).isNotNull();
        assertThat(premiunUserDto.getMoney()).isEqualTo(money + (money*2));

    }
    @Test
    public void  saveUser_superType_amount_greater_than_100()  {


        when(userRepository.save(any(User.class))).thenReturn(superUser);
        when(strategyFactory.findStrategy(any(CalculateStrategyNameEnum.class))).thenReturn(new SuperUserCalculateMoneyStrategy());
        when(userRepository.existsByEmail(any(String.class))).thenReturn(Boolean.FALSE);
        when(userRepository.existsByUserName(any(String.class))).thenReturn(Boolean.FALSE);

        Double money = superUserDto.getMoney();

        UserCreationResponseDTO newUser = userService.createUser(superUserDto);

        assertThat(newUser.getUserId()).isNotNull();
        assertThat(superUserDto.getMoney()).isEqualTo(money + (money* Double.valueOf("0.20")));

    }
}