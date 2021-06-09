package aseca.roobinhood.api.utils;

import aseca.roobinhood.api.domain.User;

public class UserMocking {

    public static User generateRawUser() {
        User user = new User();
        user.setFirstName("name");
        user.setLastName("lastname");
        user.setPassword("password");
        user.setRole("ROLE_USER");
        return user;
    }

    public static User generateRawUser(String userName) {
        final User mocked = generateRawUser();
        mocked.setEmail(userName);
        mocked.setUsername(userName);
        return mocked;
    }

}
