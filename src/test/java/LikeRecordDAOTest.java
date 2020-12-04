import db.LikeRecordDAO;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import models.LikeRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class LikeRecordDAOTest {

    public DAOTestExtension daoTestRule = DAOTestExtension.newBuilder()
            .addEntityClass(LikeRecord.class)
            .build();

    private LikeRecordDAO likeRecordDAO;

    @BeforeEach
    public void setUp() throws Exception {
        likeRecordDAO = new LikeRecordDAO(daoTestRule.getSessionFactory());
    }

    @Test
    public void createLikeRecordTestA() {
        String email1 = "april@gmail.com";
        String channelId1 = "1";
        LikeRecord record1 = new LikeRecord(email1, channelId1);
        String email2 = "april@gmail.com";
        String channelId2 = "2";
        LikeRecord record2 = new LikeRecord(email2, channelId2);
        final LikeRecord record11 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record1));
        assertThat(record1.getChannelID()).isEqualTo(channelId1);
        assertThat(record1.getEmail()).isEqualTo(email1);
        assertThat(likeRecordDAO.findAll("april@gmail.com").size()).isEqualTo(1);
        final LikeRecord record12 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record1));
        assertThat(likeRecordDAO.findAll("april@gmail.com").size()).isEqualTo(1);
        final LikeRecord record22 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record2));
        assertThat(likeRecordDAO.findAll("april@gmail.com").size()).isEqualTo(2);
    }

    @Test
    public void createLikeRecordTestB() throws Exception {
        String email = "april@gmail.com";
        String channelId = "1";
        final LikeRecord record = daoTestRule.inTransaction(() -> likeRecordDAO.create(new LikeRecord(email, channelId)));
        assertThat(record.getChannelID()).isEqualTo(channelId);
        assertThat(record.getEmail()).isEqualTo(email);
        assertThat(likeRecordDAO.findAll("april@gmail.com").size()).isEqualTo(1);
    }

    @Test
    public void findAllTestA() {
        String email1 = "april@gmail.com";
        String channelId1 = "1";
        LikeRecord record1 = new LikeRecord(email1, channelId1);
        String email2 = "april@gmail.com";
        String channelId2 = "2";
        LikeRecord record2 = new LikeRecord(email2, channelId2);
        final LikeRecord record11 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record1));
        final LikeRecord record22 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record2));
        List<LikeRecord> list1 = daoTestRule.inTransaction(() -> likeRecordDAO.findAll(null));
        assertThat(list1.size()).isEqualTo(0);
    }

    @Test
    public void findAllTestB() {
        String email1 = "april@gmail.com";
        String channelId1 = "1";
        LikeRecord record1 = new LikeRecord(email1, channelId1);
        String email2 = "april@gmail.com";
        String channelId2 = "2";
        LikeRecord record2 = new LikeRecord(email2, channelId2);
        final LikeRecord record11 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record1));
        final LikeRecord record22 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record2));
        List<LikeRecord> list1 = daoTestRule.inTransaction(() -> likeRecordDAO.findAll("april@yahoo.com"));
        assertThat(list1.size()).isEqualTo(0);
    }

    @Test
    public void findAllTestC() {
        String email1 = "april@gmail.com";
        String channelId1 = "1";
        LikeRecord record1 = new LikeRecord(email1, channelId1);
        String email2 = "april@gmail.com";
        String channelId2 = "2";
        LikeRecord record2 = new LikeRecord(email2, channelId2);
        final LikeRecord record11 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record1));
        final LikeRecord record22 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record2));
        List<LikeRecord> list1 = daoTestRule.inTransaction(() -> likeRecordDAO.findAll(email1));
        assertThat(list1.size()).isEqualTo(2);
    }

    @Test
    public void deleteRecordTestA() {
        String email1 = "april@gmail.com";
        String channelId1 = "1";
        LikeRecord record1 = new LikeRecord(email1, channelId1);
        String email2 = "april@gmail.com";
        String channelId2 = "2";
        LikeRecord record2 = new LikeRecord(email2, channelId2);
        final LikeRecord record11 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record1));
        final LikeRecord record22 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record2));
        assertThat(likeRecordDAO.findAll(email1).size()).isEqualTo(2);
        int itemDeleted = daoTestRule.inTransaction(() -> likeRecordDAO.deleteRecord(null, channelId1));
        assertThat(likeRecordDAO.findAll(email1).size()).isEqualTo(2);
        assertThat(itemDeleted).isEqualTo(0);
    }

    @Test
    public void deleteRecordTestB() {
        String email1 = "april@gmail.com";
        String channelId1 = "1";
        LikeRecord record1 = new LikeRecord(email1, channelId1);
        String email2 = "april@gmail.com";
        String channelId2 = "2";
        LikeRecord record2 = new LikeRecord(email2, channelId2);
        final LikeRecord record11 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record1));
        final LikeRecord record22 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record2));
        assertThat(likeRecordDAO.findAll(email1).size()).isEqualTo(2);
        int itemDeleted = daoTestRule.inTransaction(() -> likeRecordDAO.deleteRecord(email1, "3"));
        assertThat(likeRecordDAO.findAll(email1).size()).isEqualTo(2);
        assertThat(itemDeleted).isEqualTo(0);
    }

    @Test
    public void deleteRecordTestC() {
        String email1 = "april@gmail.com";
        String channelId1 = "1";
        LikeRecord record1 = new LikeRecord(email1, channelId1);
        String email2 = "april@gmail.com";
        String channelId2 = "2";
        LikeRecord record2 = new LikeRecord(email2, channelId2);
        final LikeRecord record11 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record1));
        final LikeRecord record22 = daoTestRule.inTransaction(() -> likeRecordDAO.create(record2));
        assertThat(likeRecordDAO.findAll(email1).size()).isEqualTo(2);
        int itemDeleted = daoTestRule.inTransaction(() -> likeRecordDAO.deleteRecord(email1, channelId1));
        assertThat(likeRecordDAO.findAll(email1).size()).isEqualTo(1);
        assertThat(itemDeleted).isEqualTo(1);
    }


}
