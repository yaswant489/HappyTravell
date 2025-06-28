package happytravell.dao;

import happytravell.model.DriverData;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DriverDaoTest {

    private DriverDao driverDao;
    private DriverData testDriver;

    @Before
    public void setUp() {
        driverDao = new DriverDao();
        testDriver = new DriverData();
        testDriver.setName("Test Driver");
        testDriver.setLicenseNumber("TEST1234");
        testDriver.setPhone("9876543210");
        testDriver.setAddress("Test Address");
        testDriver.setStatus("AVAILABLE");

        boolean inserted = driverDao.insertDriver(testDriver);
        assertTrue("Driver should be inserted successfully", inserted);
        assertTrue("Driver ID should be set", testDriver.getId() > 0);
    }

    @After
    public void tearDown() {
        if (testDriver != null && testDriver.getId() > 0) {
            driverDao.deleteDriver(testDriver.getId());
        }
    }

    @Test
    public void testGetDriverById() {
        DriverData driver = driverDao.getDriverById(testDriver.getId());
        assertNotNull("Driver should be found by ID", driver);
        assertEquals("Driver name should match", "Test Driver", driver.getName());
    }

    @Test
    public void testUpdateDriver() {
        testDriver.setPhone("1231231234");
        boolean updated = driverDao.updateDriver(testDriver);
        assertTrue("Driver should be updated", updated);

        DriverData updatedDriver = driverDao.getDriverById(testDriver.getId());
        assertEquals("Phone should be updated", "1231231234", updatedDriver.getPhone());
    }

    @Test
    public void testGetAllDrivers() {
        List<DriverData> drivers = driverDao.getAllDrivers();
        assertNotNull("Driver list should not be null", drivers);
        assertTrue("Driver list should contain at least one driver", drivers.size() > 0);
    }

    @Test
    public void testGetAvailableDrivers() {
        List<DriverData> availableDrivers = driverDao.getAvailableDrivers();
        assertNotNull("Available drivers list should not be null", availableDrivers);
        assertTrue("Should contain AVAILABLE drivers", 
                   availableDrivers.stream().anyMatch(d -> d.getId() == testDriver.getId()));
    }

    @Test
    public void testUpdateDriverStatus() {
        boolean statusUpdated = driverDao.updateDriverStatus(testDriver.getId(), "BUSY");
        assertTrue("Status should be updated", statusUpdated);

        DriverData updatedDriver = driverDao.getDriverById(testDriver.getId());
        assertEquals("Status should be BUSY", "BUSY", updatedDriver.getStatus());
    }
}
