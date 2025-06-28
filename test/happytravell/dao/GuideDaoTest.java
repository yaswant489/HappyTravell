package happytravell.dao;

import happytravell.model.GuideData;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class GuideDaoTest {

    private static GuideDao guideDao;
    private static GuideData testGuide;

    @BeforeClass
    public static void setupClass() {
        guideDao = new GuideDao();
    }

    @Before
    public void setup() {
        // Clean up any previous test guides (optional, depending on DB state)
        List<GuideData> allGuides = guideDao.getAllGuides();
        for (GuideData g : allGuides) {
            if (g.getEmail().equals("testguide@example.com")) {
                guideDao.deleteGuide(g.getGuideId());
            }
        }

        // Insert a guide for testing
        testGuide = new GuideData();
        testGuide.setGuideName("Test Guide");
        testGuide.setPhoneNumber("1234567890");
        testGuide.setAddress("Test Address");
        testGuide.setEmail("testguide@example.com");
        boolean inserted = guideDao.addGuide(testGuide);
        assertTrue("Setup guide insertion failed", inserted);

        // Refresh testGuide with DB assigned ID
        List<GuideData> guides = guideDao.getAllGuides();
        for (GuideData g : guides) {
            if ("testguide@example.com".equals(g.getEmail())) {
                testGuide.setGuideId(g.getGuideId());
                break;
            }
        }
    }

    @After
    public void tearDown() {
        if (testGuide != null && testGuide.getGuideId() != 0) {
            guideDao.deleteGuide(testGuide.getGuideId());
        }
    }

    @Test
    public void testEmailExistsForNewGuide() {
        // Email exists because testGuide inserted it
        assertTrue("Email should exist for new guide", guideDao.emailExists("testguide@example.com"));
        // Email does not exist for some random email
        assertFalse("Random email should not exist", guideDao.emailExists("randomemail@example.com"));
    }

    @Test
    public void testEmailExistsExcludingCurrentId() {
        // This should be false because excluding the current guide's id means no other guide has the email
        assertFalse("Email should not exist when excluding current ID",
            guideDao.emailExists("testguide@example.com", testGuide.getGuideId()));

        // If exclude an ID that doesn't match the test guide, it should return true
        assertTrue("Email should exist when excluding wrong ID",
            guideDao.emailExists("testguide@example.com", -1));
    }

    @Test
    public void testEmailExistsWithDifferentEmail() {
        assertFalse("Different email should not exist when excluding any ID",
            guideDao.emailExists("nonexistent@example.com", testGuide.getGuideId()));
    }
}
