package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.dto.UserDto;
import aseca.roobinhood.api.dto.security.CreateUserDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("mem")
@SpringBootTest
class UserControllerTest {

    private final UserController controller;
    private final AuthenticationController authenticationController;

    @Autowired
    UserControllerTest(UserController controller, AuthenticationController authenticationController) {
        this.controller = controller;
        this.authenticationController = authenticationController;
    }

    @Test
    public void test_001_save_user() {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .email("aseguramiento@calidad.com")
                .name("Aseguramiento")
                .lastName("Calidad")
                .password("Aseca2021")
                .username("aseca")
                .build();

        UserDto userDto = authenticationController.register(createUserDto);

        assertThat(userDto.getEmail()).isEqualTo(createUserDto.getEmail());
        assertThat(userDto.getUsername()).isEqualTo(createUserDto.getUsername());
    }

    @Test
    public void test_002_save_user_without_data() {
        CreateUserDto createUserDto = CreateUserDto.builder().build();

        UserDto userDto = authenticationController.register(createUserDto);
        assertEquals(createUserDto.getEmail(), userDto.getEmail());
        assertEquals(createUserDto.getName(), userDto.getName());
        assertEquals(createUserDto.getLastName(), userDto.getLastname());
        assertEquals(createUserDto.getUsername(), userDto.getUsername());
    }

    @Test
    public void test_003_update_user_data() {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .email("aseguramiento@calidad.com")
                .name("Aseguramiento")
                .lastName("Calidad")
                .password("Aseca2021")
                .username("aseca")
                .build();

        UserDto userDto = authenticationController.register(createUserDto);
        UserDto newUserDto = UserDto.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .username(userDto.getUsername())
                .build();
        UserDto userUpdated = controller.update(newUserDto);

        assertEquals(newUserDto.getEmail(), userUpdated.getEmail());
        assertEquals(newUserDto.getName(), userUpdated.getName());
        assertEquals(newUserDto.getLastname(), userUpdated.getLastname());
        assertEquals(newUserDto.getUsername(), userUpdated.getUsername());
    }
}