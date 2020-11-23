package resources;

import db.LikeRecordDAO;
import io.dropwizard.hibernate.UnitOfWork;
import models.LikeRecord;

import javax.validation.Valid;
import javax.ws.rs.*;
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

    @GET
    @UnitOfWork
    public List<LikeRecord> listLikes(String email) {
        return likeRecordDAO.findAll(email);
    }

    @POST
    @UnitOfWork
    @Path("/{email}/{channelId}")
    public void getLikeInfo(@PathParam("channelId") String channelId, @PathParam("email") String email) {
        LikeRecord record = new LikeRecord(email, channelId);
        createLikeRecord(record);
        System.out.println(email + " -> " + channelId);
        List<LikeRecord> allLikes = listLikes(email);
        for(LikeRecord r : allLikes){
            System.out.println("record is: " + r.getEmail() + " -> " + r.getChannelID());
        }
    }
}
