import happytravell.dao.PasswordResetDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PasswordResetDaoTest {

    private PasswordResetDao dao;
    private final String correctEmail = "yaswantpoudel0@gmail.com";
    private final String correctPassword = "123";

    @Before
    public void setUp() {
        dao = new PasswordResetDao();  // Use no-arg constructor, as your DAO expects
    }

    @Test
    public void resetWithValidEmail() {
        boolean result = dao.resetPassword(correctEmail, correctPassword);
        Assert.assertTrue("Password should be updated successfully", result);
    }

    @Test
    public void resetWithInvalidEmail() {
        boolean result = dao.resetPassword("invalid@example.com", "123456");
        Assert.assertFalse("Password should not be updated for invalid email", result);
    }
}
