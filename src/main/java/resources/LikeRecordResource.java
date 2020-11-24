package resources;

import db.LikeRecordDAO;
import io.dropwizard.hibernate.UnitOfWork;
import models.LikeRecord;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


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
    public List<LikeRecord> listLikes(@PathParam("email") String email) {
        return likeRecordDAO.findAll(email);
    }

    @POST
    @UnitOfWork
    @Path("/{email}/{channelId}")
    public void getLikeInfo(@PathParam("channelId") String channelId, @PathParam("email") String email) {
        LikeRecord record = new LikeRecord(email, channelId);
        List<LikeRecord> allLikes = listLikes(email);
        boolean duplicate = false;
        for(LikeRecord records : allLikes){
            if(records.getChannelID().equals(channelId) && records.getEmail().equals(email)) {
                duplicate = true;
                break;
            }
        }
        if(!duplicate) createLikeRecord(record);
        for(LikeRecord r : allLikes){
            System.out.println("record is: " + r.getEmail() + " -> " + r.getChannelID());
        }
    }
}
