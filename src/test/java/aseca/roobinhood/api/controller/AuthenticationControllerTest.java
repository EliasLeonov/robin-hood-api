package aseca.roobinhood.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@ActiveProfiles("mem")
@SpringBootTest
public class AuthenticationControllerTest {

    private final AuthenticationController authenticationController;

    @Autowired
    public AuthenticationControllerTest(AuthenticationController authenticationController) {
        this.authenticationController = authenticationController;
    }
}