package resources;

import db.LikeRecordDAO;
import io.dropwizard.hibernate.UnitOfWork;
import models.LikeRecord;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/LikeRecord")
@Produces(MediaType.APPLICATION_JSON)
public class LikeRecordResource {

    private final LikeRecordDAO likeRecordDAO;

    public LikeRecordResource(LikeRecordDAO likeRecordDAO) {
        this.likeRecordDAO = likeRecordDAO;
    }

    @POST
    @UnitOfWork
    public LikeRecord createLikeRecord(@Valid LikeRecord record) {
        return likeRecordDAO.create(record);
    }

    @POST
    @UnitOfWork
    @Path("/addRecord/{email}/{channelId}")
    public void getLikeInfo(@PathParam("channelId") String channelId, @PathParam("email") String email) {
        LikeRecord record = new LikeRecord(email, channelId);
        List<LikeRecord> allLikes = listLikes(email);
        boolean duplicate = false;
        for (LikeRecord records : allLikes) {
            if (records.getChannelID().equals(channelId) && records.getEmail().equals(email)) {
                duplicate = true;
                break;
            }
        }
        if (!duplicate) createLikeRecord(record);
/*        for (LikeRecord r : allLikes) {
            System.out.println("record is: " + r.getEmail() + " -> " + r.getChannelID());
        }*/
    }

    public List<LikeRecord> listLikes(String email) {
        return likeRecordDAO.findAll(email);
    }

    @POST
    @UnitOfWork
    @Path("deleteRecord/{email}/{channelId}")
    public void deleteLikeInfo(@PathParam("channelId") String channelId, @PathParam("email") String email) {
        System.out.println("record to be deleted: " + email + " -> " + channelId);
        int deleteStatus = likeRecordDAO.deleteRecord(email, channelId);
        System.out.println("#entry deleted: " + deleteStatus);
        List<LikeRecord> allLikes = listLikes(email);
        for (LikeRecord r : allLikes) {
            System.out.println("record is: " + r.getEmail() + " -> " + r.getChannelID());
        }
    }
}
