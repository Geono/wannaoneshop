package market.wannaone.security;

import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordTest {
    @Test
    public void encodingTest() throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        boolean matches = passwordEncoder.matches(
                "1234",
                "{bcrypt}$2a$10$7Tu2UN/U2C24g8vCRTppwO9hmLUHFRpCBt9nZyYOLRRoufNt5rn4C");
        System.out.println(matches);
    }
}
