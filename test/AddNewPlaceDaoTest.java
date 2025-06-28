import happytravell.dao.AddNewPlaceDao;
import happytravell.model.PlaceData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddNewPlaceDaoTest {

    private AddNewPlaceDao dao;

    @Before
    public void setUp() {
        dao = new AddNewPlaceDao();
    }

    @Test
    public void testRegisterNewPlace() {
      
        byte[] testImage = new byte[]{10, 20, 30, 40, 50};

        
        PlaceData testPlace = new PlaceData("JUnit Test Place", "Test description from unit test.", testImage);

       
        boolean result = dao.Register(testPlace);

        
        Assert.assertTrue("The new place should be inserted into the database.", result);
    }
}
