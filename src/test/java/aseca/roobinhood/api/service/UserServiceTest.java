package aseca.roobinhood.api.service;

import aseca.roobinhood.api.dto.UserDto;
import aseca.roobinhood.api.dto.security.CreateUserDto;
import aseca.roobinhood.api.repository.UserRepository;
import aseca.roobinhood.api.utils.UserMocking;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("mem")
@SpringBootTest
class UserServiceTest {

    private final UserService service;
    private final UserRepository repository;
    private final AuthenticationService authenticationService;

    @Autowired
    UserServiceTest(UserService service, UserRepository repository, AuthenticationService authenticationService) {
        this.service = service;
        this.repository = repository;
        this.authenticationService = authenticationService;
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

        UserDto userDto = authenticationService.register(createUserDto);

        assertThat(userDto.getEmail()).isEqualTo(createUserDto.getEmail());
        assertThat(userDto.getUsername()).isEqualTo(createUserDto.getUsername());
    }

    @Test
    public void test_002_save_user_without_data() {
        CreateUserDto createUserDto = CreateUserDto.builder().build();

        UserDto userDto = authenticationService.register(createUserDto);
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

        UserDto userDto = authenticationService.register(createUserDto);
        UserDto newUserDto = UserDto.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .username(userDto.getUsername())
                .build();
        UserDto userUpdated = service.update(newUserDto);

        assertEquals(newUserDto.getEmail(), userUpdated.getEmail());
        assertEquals(newUserDto.getName(), userUpdated.getName());
        assertEquals(newUserDto.getLastname(), userUpdated.getLastname());
        assertEquals(newUserDto.getUsername(), userUpdated.getUsername());
    }

    @Test
    @WithMockUser("asecal")
    public void test_004_getUserLogged(){
        repository.save(UserMocking.generateRawUser("asecal"));
        UserDto userDto = service.getUserLogged();
        assertThat(userDto).isNotNull();
        assertThat(userDto.getUsername()).isEqualTo("asecal");
    }

    @Test
    public void test_005_deleteUser(){

        CreateUserDto createUserDto = CreateUserDto.builder()
                .email("aseguramiento@calidad.com")
                .name("Aseguramiento")
                .lastName("Calidad")
                .password("Aseca2021")
                .username("aseca")
                .build();

        UserDto userDto = authenticationService.register(createUserDto);
        service.delete(userDto.getId());

        assertThat(repository.findById(userDto.getId())).isEqualTo(Optional.empty());

    }
}