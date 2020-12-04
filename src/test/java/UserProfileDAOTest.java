import db.UserProfileDAO;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import models.InfluencerProfile;
import models.UserProfile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class UserProfileDAOTest {

    public DAOTestExtension daoTestRule = DAOTestExtension.newBuilder()
            .addEntityClass(UserProfile.class)
            .build();

    private UserProfileDAO userProfileDAO;

    @BeforeEach
    public void setUp() throws Exception {
        userProfileDAO = new UserProfileDAO(daoTestRule.getSessionFactory());
    }

    @Test
    public void createUserTestA() {
        UserProfile record1 = new UserProfile(null, "april@gmail.com");
        final UserProfile record11 = daoTestRule.inTransaction(() -> userProfileDAO.createUser(record1));
        assertThat(userProfileDAO.getAllUsers().size()).isEqualTo(0);
        assertThat(record11).isEqualTo(null);
    }

    @Test
    public void createUserTestB() {
        UserProfile record1 = new UserProfile("April", "april@gmail.com");
        final UserProfile record11 = daoTestRule.inTransaction(() -> userProfileDAO.createUser(record1));
        assertThat(userProfileDAO.getAllUsers().size()).isEqualTo(1);
        assertThat(record11.getEmail()).isEqualTo(record1.getEmail());
        assertThat(record11.getName()).isEqualTo(record1.getName());
    }

    @Test
    public void getAllUsersTest() {
        UserProfile record1 = new UserProfile("April", "april@gmail.com");
        final UserProfile record11 = daoTestRule.inTransaction(() -> userProfileDAO.createUser(record1));
        UserProfile record2 = new UserProfile("Sicily", "sicily@gmail.com");
        final UserProfile record22 = daoTestRule.inTransaction(() -> userProfileDAO.createUser(record2));
        assertThat(userProfileDAO.getAllUsers().size()).isEqualTo(2);
    }
}
