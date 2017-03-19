package biz.bagira.auction.entities;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.*;

/**
 * Created by Dmitriy on 17.03.2017.
 */
public class UserTest {


    @Test
    public void testCreationUser() throws Exception {
        UserBuilder builder = new UserBuilder();
        User user = builder.id(1).build();
        assertEquals((int)user.getIdUsers(),1);
        assertNotNull(user.getIdUsers());
        assertNull(user.getCity());
        assertNull(user.getPassword());
        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertTrue(user.getBidList().isEmpty());
        assertFalse(user.getValidateEmail());
        assertEquals(user.getState(),State.ACTIVE.getState());
    }

}