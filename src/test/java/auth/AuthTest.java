package auth;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuthTest {
    @Test
    public void authenticate() throws Exception {
        Auth auth = new Auth();
        String username = "Eugene";
        String password = "123";
        auth.addUser(username, password, true);

        // Asserting authenticate with true user auth
        assertTrue(auth.authenticate(username, password));

        // Asserting authenticate with wrong user auth
        assertFalse(auth.authenticate(username, "Wrong pass"));
        assertFalse(auth.authenticate("Wrong", password));
        assertFalse(auth.authenticate("Wrong", "Wrong pass"));
    }

    @Test
    public void isVacantUsername() throws Exception {
        Auth auth = new Auth();
        String username = "Eugene";
        auth.addUser(username, "123", true);

        // Asserting vacant username
        assertTrue(auth.isVacantUsername("Vacant"));

        // Asserting occupied username
        assertFalse(auth.isVacantUsername(username));
    }

    @Test
    public void addUser() throws Exception {
        Auth auth = new Auth();
        String username = "Eugene";
        auth.addUser(username, "123", true);

        // Asserting occupied username
        assertFalse(auth.isVacantUsername(username));
    }

}